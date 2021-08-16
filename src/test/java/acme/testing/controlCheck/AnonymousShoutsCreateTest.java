package acme.testing.controlCheck;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.helpers.StringHelper;
import acme.testing.AcmePlannerTest;

public class AnonymousShoutsCreateTest extends AcmePlannerTest {
	
	/*	Feature: un usuario anónimo puede crear gritos
	 * 	Caso positivo.
	*/
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void create(final int recordIndex, final String author, final String text, final String info,
		final String atributo1, final String atributo2, final String atributo3, final String atributo4) {
		
		assert !StringHelper.isBlank(author);
		assert !StringHelper.isBlank(text);
		assert !StringHelper.isBlank(atributo3);
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("sheet.name", atributo1);
		super.fillInputBoxIn("sheet.deadline", atributo2);
		super.fillInputBoxIn("sheet.budget", atributo3);
		super.fillInputBoxIn("sheet.important", atributo4);
		super.clickOnSubmitButton("Shout!");
		 
		super.checkSimplePath("/master/welcome");
		
		super.clickOnMenu("Anonymous", "Shout list");	
				
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		super.checkColumnHasValue(recordIndex, 4, atributo1);
		super.checkColumnHasValue(recordIndex, 5, atributo2);
		super.checkColumnHasValue(recordIndex, 6, atributo3);
		super.checkColumnHasValue(recordIndex, 7, atributo4);
		
	}
	
	/*	Feature: un usuario anónimo puede crear gritos
	 * 	Caso negativo.
	 * 		Primer update: autor y texto vacío. Información mala URL.
	 * 		Segundo update: autor superando el máximo de caracteres, texto con umbral de spam superado y mala URL.
	 * 		Tercer update: autor y texto menor del mínimo de caracteres, y mala URL.
	 * 		Cuarto update: autor vacío, texto superando el máximo de caracteres y mala URL.
	 * 		Quinto update: autor vacío, texto con umbral de spam superado y mala URL.
	*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	@Disabled
	public void createNegative(final String author, final String text, final String info,
		final String atributo1, final String atributo2, final String atributo3, final String atributo4) {
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("sheet.name", atributo1);
		super.fillInputBoxIn("sheet.deadline", atributo2);
		super.fillInputBoxIn("sheet.budget", atributo3);
		super.fillInputBoxIn("sheet.important", atributo4);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
		super.checkSimplePath("/anonymous/shout/create");
		
	}
	
	

}
