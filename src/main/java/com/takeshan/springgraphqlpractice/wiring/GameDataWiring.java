package com.takeshan.springgraphqlpractice.wiring;

import com.takeshan.springgraphqlpractice.entity.Company;
import com.takeshan.springgraphqlpractice.entity.Game;
import com.takeshan.springgraphqlpractice.repository.CompanyRepository;
import com.takeshan.springgraphqlpractice.repository.GameRepository;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.graphql.boot.RuntimeWiringBuilderCustomizer;
import org.springframework.stereotype.Component;

@Component
public class GameDataWiring implements RuntimeWiringBuilderCustomizer {

    private GameRepository gameRepository;
    private CompanyRepository companyRepository;

    public GameDataWiring(GameRepository gameRepository, CompanyRepository companyRepository) {
        this.gameRepository = gameRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void customize(RuntimeWiring.Builder builder) {
        builder.type("Query", builder1 ->
                builder1.dataFetcher("game", env -> {
                    Integer id = Integer.valueOf(env.getArgument("id"));
                    return gameRepository.findById(id);
                })
                .dataFetcher("games", env -> {
                    return gameRepository.findAll();
                })
                .dataFetcher("company", env -> {
                    Integer companyId = Integer.valueOf(env.getArgument("companyId"));
                    return companyRepository.findById(companyId);
                })
                .dataFetcher("companies", env -> {
                    return companyRepository.findAll();
                })
        );

        builder.type("Company", builder1 ->
                builder1.dataFetcher("games", env -> {
                    Company company = env.getSource();
                    return gameRepository.findByCompanyName(company.getName());
                })
        );

        builder.type("Mutation", builder1 ->
                builder1.dataFetcher("createCompany", env -> {
                    Integer companyId = Integer.valueOf(env.getArgument("companyId"));
                    String companyName = env.getArgument("name");

                    Company company = new Company();
                    company.setCompanyId(companyId);
                    company.setName(companyName);

                    return companyRepository.save(company);
                })
                .dataFetcher("createGame", env -> {
                    Integer id = Integer.valueOf(env.getArgument("id"));
                    String gameName = env.getArgument("name");
                    String companyName = env.getArgument("companyName");
                    String gameKind = env.getArgument("kind");

                    Game game = new Game();
                    game.setId(id);
                    game.setName(gameName);
                    game.setCompanyName(companyName);
                    game.setKind(gameKind);

                  return gameRepository.save(game);
                })
        );
    }
}
