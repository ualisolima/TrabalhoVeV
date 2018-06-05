package com.br.ufc.vev;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.ufc.vev.model.Admin;
import com.br.ufc.vev.repository.AdminRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class AdminTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void inserirAdminCorretamente() {
    	Admin admin = new Admin();
    	admin.setLogin("testeLogin");
    	admin.setSenha("testeSenha");
        this.entityManager.persist(admin);
        Admin admin2 = adminRepository.findByLogin(admin.getLogin()).get(0);
        assertThat(admin2.getSenha()).isEqualTo(admin.getSenha());
        
    }
    
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//    
//    @Test
//    public void inserirAdminSemLogin() {
//    	Admin admin = new Admin();
//    	admin.setSenha("testeSenha");
//    	
//        this.entityManager.persist(admin);
//        //Admin admin2 = adminRepository.findByLogin(admin.getLogin()).get(0);
//        //assertThat(admin2.getSenha()).isEqualTo(admin.getSenha());
//        
//    }
}
