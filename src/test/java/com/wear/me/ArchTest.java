package com.wear.me;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.wear.me");

        noClasses()
            .that()
            .resideInAnyPackage("com.wear.me.service..")
            .or()
            .resideInAnyPackage("com.wear.me.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.wear.me.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
