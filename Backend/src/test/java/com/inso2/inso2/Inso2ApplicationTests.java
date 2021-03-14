package com.inso2.inso2;

import com.inso2.inso2.domain.entity.Category;
import com.inso2.inso2.domain.persistence.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Inso2ApplicationTests {

	@Autowired
	CategoryRepository categoryRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void createCategory() throws Exception {
		Category c  = new Category("Testing");
		categoryRepository.save(c);
		assertEquals(2, categoryRepository.count());
	}





}
