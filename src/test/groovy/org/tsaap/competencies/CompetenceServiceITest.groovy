package org.tsaap.competencies

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.validation.ConstraintViolationException

/**
 * Test competence service
 */
@ContextConfiguration
@SpringBootTest
class CompetenceServiceITest extends Specification {

    @Autowired
    private CompetenceService competenceService

    def "test save a valid catalog"() {
        given: "a valid catalog"
        Catalog catalog = new Catalog();
        catalog.setName("Catalog 1");
        catalog.setDescription("Description 1");

        when: "the catalog is saved"
        competenceService.saveCatalog(catalog);

        then: "the catalog has an id"
        catalog.getId() != null
        catalog.getName() == "Catalog 1"
        catalog.getDescription() == "Description 1"
    }
    def "test save a non valid catalog"() {
        given: "a non valid catalog"
        Catalog catalog = new Catalog();
        catalog.setName("C1");
        catalog.setDescription("Description 1");

        when: "the catalog is saved"
        competenceService.saveCatalog(catalog);

        then: "the a validation exception is thrown"
        thrown ConstraintViolationException
    }
    def "test save a valid category"() {
        given: "a valid category"
        Category category = new Category();
        category.setId(1)
        category.setName("category 1");
        category.setDescription("Description 1");

        when: "the category is saved"
        competenceService.saveCategory(category);

        then: "the category has an id"
        category.getId() == 1
        category.getName() == "category 1"
        category.getDescription() == "Description 1"
    }
    def "test save a non valid category"() {
        given: "a non valid category"
        Category category = new Category();
        category.setName("C2");
        category.setDescription("Description 2");

        when: "the category is saved"
        competenceService.saveCategory(category);

        then: "the a validation exception is thrown"
        thrown ConstraintViolationException
    }
    def "test save a valid competence"() {
        given: "a valid competence"
        Competence competence = new Competence();
        Category category1=new Category();
        Catalog catalog1=new Catalog();
        category1.setId(1)
        category1.setName("category1");
        category1.setDescription("Desc1");
        catalog1.setName("cata1");
        catalog1.setDescription("Descr1");
        competence.setId(1);
        competence.setName("competence 1");
        competence.setDescription("Description 1");
        competence.setCatalog(catalog1);
        competence.setCategory(category1);
        when: "the competence is saved"
        competenceService.saveCatalog(catalog1)
        competenceService.saveCategory(category1)
        competenceService.saveCompetence(competence);

        then: "the competence has an id"
        competence.getId() == 1
        competence.getName() == "competence 1"
        competence.getDescription() == "Description 1"
        competence.getCatalog().getDescription() == "Descr1"
        competence.getCatalog().getName() == "cata1"
        competence.getCategory().getId() == 1
        competence.getCategory().getName() == "category1"
        competence.getCategory().getDescription() == "Desc1"
    }
    def "test save a non valid competence"() {
        given: "a non valid competence"
        Competence competence = new Competence();
        competence.setName("C3");
        competence.setDescription("Description 3");

        when: "the competence is saved"
        competenceService.saveCompetence(competence);

        then: "the a validation exception is thrown"
        thrown ConstraintViolationException
    }

}
