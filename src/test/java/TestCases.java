
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Gpay.AppConfig;
import Gpay.controller.PaymentPresentation;
import Gpay.dao.AccountDetailsDAO;
import Gpay.dao.ProductDetailsDAO;
import Gpay.entities.Account;
import Gpay.entities.Product;
import Gpay.service.PaymentService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class TestCases {
	
	public static double marks = 1;
	
	ApplicationContext ctx;
	PaymentPresentation paymentPresentation;
	Account account;
	Product product;
	
	@BeforeAll
	void setUp() throws Exception {
		ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		marks++;
	}
	
	@AfterAll
	void tearDown() throws Exception {
		AnnotationConfigApplicationContext anac = (AnnotationConfigApplicationContext) ctx;
		anac.close();
	}
	
	@Test
	@Order(1)
	@DisplayName("test for PaymentService dependecy injected to PaymentPresentation")
	void testPaymentPresentation() throws Exception{
	    // Arrange
		paymentPresentation = ctx.getBean(PaymentPresentation.class);
	    
	    // Act
	    boolean isPaymentServiceDependencyInjected = checkDependencyInjection(paymentPresentation, PaymentService.class);
		
		// Assert
		assertTrue(isPaymentServiceDependencyInjected,
				() -> "Failure message: Dependency is not properly injected into the PaymentPresentation class");
		marks++;
	}

	@Test
	@Order(2)
	@DisplayName("test for AccountDetailsDAO and ProductDetailsDAO dependecies injected to PaymentService")
	void testPaymentService() throws Exception{
	    // Arrange
		PaymentService paymentService = ctx.getBean(PaymentService.class);
	    
	    // Act
	    boolean isAccountDetailsDAODependencyInjected = checkDependencyInjection(paymentService, AccountDetailsDAO.class);
	    boolean isProductDetailsDAODependencyInjected = checkDependencyInjection(paymentService, ProductDetailsDAO.class);
		
		// Assert
		assertTrue(isAccountDetailsDAODependencyInjected,
				() -> "Failure message: Dependency is not properly injected into the PaymentService class");
		
		assertTrue(isProductDetailsDAODependencyInjected,
				() -> "Failure message: Dependency is not properly injected into the PaymentService class");
		marks++;
	}
	
	@Test
	@Order(3)
	@DisplayName("test for Product dependecy injected to ProductDetailsDAO")
	void testProductDetailsDAO() throws Exception{
	    // Arrange
		ProductDetailsDAO productDetailsDAO = ctx.getBean(ProductDetailsDAO.class);
	    
	    // Act
	    boolean isProductDependencyInjected = checkDependencyInjection(productDetailsDAO, Product.class);
		
		// Assert
		assertTrue(isProductDependencyInjected,
				() -> "Failure message: Dependency is not properly injected into the ProductDetailsDAO class");
		marks++;
	}
	
	@Test
	@Order(3)
	@DisplayName("test for Account dependecy injected to AccountDetailsDAO")
	void testAccountDetailsDAO() throws Exception{
	    // Arrange
		AccountDetailsDAO accountDetailsDAO = ctx.getBean(AccountDetailsDAO.class);
	    
	    // Act
	    boolean isAccountDependencyInjected = checkDependencyInjection(accountDetailsDAO, Account.class);
		
		// Assert
		assertTrue(isAccountDependencyInjected,
				() -> "Failure message: Dependency is not properly injected into the AccountDetailsDAO class");
		marks++;
	}
	
	@Test
	@Order(4)
	@DisplayName("test for Account and Product bean creation")
	void testAccountAndProductBean() throws Exception{
	    // Arrange
		account = ctx.getBean(Account.class);
		product = ctx.getBean(Product.class);
	    
	    // Act and Assert
		assertNotNull(account,
				() -> "Failure message: Account bean not created");
		
		assertNotNull(product,
				() -> "Failure message: Product bean not created");
		marks++;
	}
	
	@Test
	@Order(5)
	@DisplayName("when sufficient balance avilable in account for buying product")
	void testshowPayment_whenSufficientBalanceForBuyingProduct_thenReturnTotalPaymentAmount() throws Exception{
	    // Arrange and Act
		double totalPayment = (double) PaymentPresentation.class.getDeclaredMethod("showPayment", int.class).invoke(paymentPresentation, 13);
		
		// Assert
		assertEquals(9.99*13, totalPayment,
				() -> "Failure message: calculated wrong amount");
		
		assertEquals(1000.00 - 9.99*13, account.getAccountBalance(),
				() -> "Failure message: Account balance not updated after payment");
		
		assertEquals(26 - 13, product.getAvailableQuantity(),
				() -> "Failure message: Quantites are not updated after payment");
		marks++;
	}
	
	@Test
	@Order(5)
	@DisplayName("when insufficient balance avilable in account for buying product")
	void testshowPayment_whenInsufficientBalanceForBuyingProduct_thenThrowPaymentException() throws Exception{
	    // Arrange and Act and Assert
		assertThrows(Exception.class,
				() -> PaymentPresentation.class.getDeclaredMethod("showPayment", int.class).invoke(paymentPresentation, 1000),
				() -> "Failure message: Expected to be throw payment exception when buying amount greater than account balance");
		marks++;
	}
	
	@Test
	@Order(5)
	@DisplayName("when insufficient products available for buying product")
	void testshowPayment_whenInsufficientQuantitiesOfProduct_thenThrowPaymentException() throws Exception{
	    // Arrange and Act and Assert
		assertThrows(Exception.class,
				() -> PaymentPresentation.class.getDeclaredMethod("showPayment", int.class).invoke(paymentPresentation, 95),
				() -> "Failure message: Expected to be throw payment exception when buying quantity greater than available quantities");
		marks++;
	}
	
	@Test
    @Order(9)
    void buildScore(){
        System.out.println("[MARKS] marks is " + marks);
    }
	
	private boolean checkDependencyInjection(Object obj, Class<?> dependentObject) throws Exception {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        boolean isDependencyInjected = false;
        for (Field field : fields) {
            field.setAccessible(true);
			if(field.get(obj).getClass().getSimpleName().equals(dependentObject.getSimpleName())) { 
				isDependencyInjected = true;
				break;
			}
        }
        return isDependencyInjected;
    }
}
