package edu.vanderbilt.cs278.grouppic;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.config.ResourceMappingConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.MongoURI;

import edu.vanderbilt.cs278.grouppic.json.ResourcesMapper;
import edu.vanderbilt.cs278.grouppic.repository.Caption;
import edu.vanderbilt.cs278.grouppic.repository.CaptionRepository;
import edu.vanderbilt.cs278.grouppic.repository.Picture;
import edu.vanderbilt.cs278.grouppic.repository.PictureRepository;

//Tell Spring to automatically inject any dependencies that are marked in
//our classes with @Autowired
@EnableAutoConfiguration
// Tell Spring to automatically create a JPA implementation of our
// VideoRepository
//@EnableJpaRepositories(basePackageClasses = {PictureRepository.class, CaptionRepository.class})
// Tell Spring to turn on WebMVC (e.g., it should enable the DispatcherServlet
// so that requests can be routed to our Controllers)
@EnableWebMvc
// Tell Spring that this object represents a Configuration for the
// application
@Configuration
// Tell Spring to go and scan our controller package (and all sub packages) to
// find any Controllers or other components that are part of our applciation.
// Any class in this package that is annotated with @Controller is going to be
// automatically discovered and connected to the DispatcherServlet.
@ComponentScan
@EnableMongoRepositories
//@Import(RepositoryRestMvcConfiguration.class)
public class Application {

	// Tell Spring to launch our app!
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// We are overriding the bean that RepositoryRestMvcConfiguration
	// is using to convert our objects into JSON so that we can control
	// the format. The Spring dependency injection will inject our instance
	// of ObjectMapper in all of the spring data rest classes that rely
	// on the ObjectMapper. This is an example of how Spring dependency
	// injection allows us to easily configure dependencies in code that
	// we don't have easy control over otherwise.
	//
	// Normally, we would not override this object mapping. However, in this
	// case, we are overriding the JSON conversion so that we can easily
	// extract a list of videos, etc. using Retrofit. You can remove this
	// method from the class to see what the default HATEOAS-based responses
	// from Spring Data Rest look like. You will need to access the server
	// from your browser as removing this method will break the Retrofit
	// client.
	//
	// See the ResourcesMapper class for more details.
	
	/*@Autowired
	private ObjectMapper myObjectMapper;
	@Bean*/
	
	@Bean	
	public ObjectMapper resourceMapper() {
		return new ResourcesMapper();
	}
	
    @SuppressWarnings("deprecation")
	public @Bean MongoDbFactory mongoDbFactory() throws MongoException, UnknownHostException {
        return new SimpleMongoDbFactory(new MongoURI("mongodb://grouppic:cs278@ds045087.mongolab.com:45087/grouppic"));
    }

    public @Bean MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
	
	
	/*@Bean
	@Override
	public ObjectMapper objectMapper() {
		return new ResourcesMapper();
	}*/
/*
	@Override
	protected void configureRepositoryRestConfiguration(
			RepositoryRestConfiguration config) {
		config.exposeIdsFor(Picture.class, Caption.class );
	}
	*/

}
