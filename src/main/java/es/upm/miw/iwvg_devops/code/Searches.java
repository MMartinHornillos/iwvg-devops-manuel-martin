package es.upm.miw.iwvg_devops.code;

import java.util.stream.Stream;

public class Searches {

    public Stream<String> findUserFamilyNameByImproperFraction() {
        return new UsersDatabase().findAll().filter(
                    user -> user.getFractions().stream()
                        .anyMatch(Fraction::isImproper)
                ).map(User::getFamilyName);
    }

    public Fraction findFractionAdditionByUserId(String id) {
        return new UsersDatabase().findAll().filter(user -> user.getId().equals(id))
                .flatMap(user -> user.getFractions().stream())
                .reduce(Fraction::add).orElse(new Fraction(0, 0));
    }

    public Stream<String> findUserFamilyNameInitialByAnyProperFraction() {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream().anyMatch(Fraction::isProper))
                .map(user -> user.getFamilyName().substring(0, 1));
    }

    public Fraction findFractionSubtractionByUserName(String name) {
        return new UsersDatabase().findAll().filter(user -> user.getName().equals(name))
                .flatMap(user -> user.getFractions().stream())
                .reduce(Fraction::subtract).orElse(new Fraction(0, 0));
    }

}
