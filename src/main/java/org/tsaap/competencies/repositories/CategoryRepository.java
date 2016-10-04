package org.tsaap.competencies.repositories;

import org.springframework.data.repository.CrudRepository;
import org.tsaap.competencies.Category;

/**
 * Category repository
 */
public interface CategoryRepository extends CrudRepository<Category,Long> {
}
