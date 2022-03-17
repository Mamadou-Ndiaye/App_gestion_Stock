package sn.ucad.gestionstock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sn.ucad.gestionstock.dto.AdresseDto;
import sn.ucad.gestionstock.dto.RolesDto;
import sn.ucad.gestionstock.dto.UtilisateurDto;
import sn.ucad.gestionstock.model.Roles;
import sn.ucad.gestionstock.repository.RolesRepository;
import sn.ucad.gestionstock.services.UtilisateurService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@EnableJpaAuditing
@SpringBootApplication
public class GestionstockApplication implements CommandLineRunner {

	@Autowired
	UtilisateurService  utilisateurService;

	@Autowired
	RolesRepository rolesRepository;

	public static void main(String[] args) {
		SpringApplication.run(GestionstockApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<RolesDto>  rolesDtos = new ArrayList<>();

		Roles admin = rolesRepository.save(new Roles(null, "ADMIN", null, null));
		RolesDto adminDto = RolesDto.fromEntity(admin);
		Roles user1 = rolesRepository.save(new Roles(null, "USER", null, null));
		RolesDto user1Dto = RolesDto.fromEntity(user1);
		Roles user2 = rolesRepository.save(new Roles(null, "USER", null, null));
		RolesDto user2Dto = RolesDto.fromEntity(user2);


		rolesDtos.add(adminDto);

		AdresseDto adresse1Dto = new AdresseDto("Guédiawaye","Darou","Dakar","5005","SEN");
		String nom ="Ndiaye";
		UtilisateurDto utilisateurDto = new UtilisateurDto();
		utilisateurDto.setMotDePasse("Passer123");
		utilisateurDto.setPrenom("Mamadou");
		utilisateurDto.setMail("mamadou.ndiaye@yopmail.com");
		utilisateurDto.setRolesDtos(rolesDtos);
		utilisateurDto.setAdresseDto(adresse1Dto);
		utilisateurDto.setPhoto("maPhoto");
		utilisateurService.save(utilisateurDto);

		utilisateurService.save(new UtilisateurDto(null,"Sy","Mouhamed","mouhamed@yopmail.com","Passer123","maPhoto",new AdresseDto("K Masseur","Diamniadio","Dakar","5005","SEN"),new Date(),true,null,rolesDtos,null));
		utilisateurService.save(new UtilisateurDto(null,"Diop","Fatou","fatou@yopmail.com","Passer123","maPhoto",new AdresseDto("Nibodji","Pikine","Kaolack","5005","SEN"),new Date(),true,null,rolesDtos,null));
		utilisateurService.save(new UtilisateurDto(null,"Fall","Astou","astou@yopmail.com","Passer123","maPhoto",new AdresseDto("Touba","Pikine","Dakar","5005","SEN"),new Date(),true,null,rolesDtos,null));


	}
}
