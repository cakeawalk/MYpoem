package com.extramarks.ExtramarksHOAdmin.fieldManagement.controller;



	import static org.junit.Assert.assertEquals;
	import static org.mockito.BDDMockito.given;
	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
	import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

	import java.util.ArrayList;
	import java.util.List;

	import org.json.JSONArray;
	import org.json.JSONObject;
	import org.junit.jupiter.api.Disabled;
	import org.junit.jupiter.api.DisplayName;
	import org.junit.jupiter.api.MethodOrderer;
	import org.junit.jupiter.api.Order;
	import org.junit.jupiter.api.Test;
	import org.junit.jupiter.api.TestMethodOrder;
	import org.junit.jupiter.api.extension.ExtendWith;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.http.MediaType;
	import org.springframework.test.context.junit.jupiter.SpringExtension;
	import org.springframework.test.web.servlet.MockMvc;
	import org.springframework.test.web.servlet.MvcResult;

	import com.extramarks.ExtramarksHOAdmin.dto.RoleServicePermissionDTO;
	import com.extramarks.ExtramarksHOAdmin.dto.ServiceFieldDTO;
	import com.extramarks.ExtramarksHOAdmin.model.fieldModel;
import com.extramarks.ExtramarksHOAdmin.model.FieldModel;
import com.extramarks.ExtramarksHOAdmin.model.ServiceFieldModel;
	import com.extramarks.ExtramarksHOAdmin.model.ServiceModel;
	import com.extramarks.ExtramarksHOAdmin.utils.RestConstants;
	import com.fasterxml.jackson.databind.ObjectMapper;

	@ExtendWith(SpringExtension.class)
	@SpringBootTest/*(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)(properties = { "example.firstProperty=annotation" })*/
	@AutoConfigureMockMvc
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	public class FieldManagementControllerTestIT {


		   @Autowired
		   private MockMvc mvc;
		   
		   /**
		    * <h1>SaveServiceTest</h1>
		    * <p>Add Parameter Values in the Post Request.In this test the Data is send and is validated and then added to the database</p>
		    *@since 1.0.0.0
		    * 
		    */
		   
		   
		   @Test
		   @DisplayName("Save Role Test")
		   @Order(1)
		   public void saveserviceTest_ITest() {

		    	List<FieldModel> fieldModels = new ArrayList<FieldModel>();
				FieldModel fieldModel = new FieldModel();
		        
			
							
				{	
					
					fieldModel.setDescription("kuldeeptest");
					fieldModel.setFieldId(2);
					fieldModel.setName("kuldeep");
					
					fieldModels.add(fieldModel);
					
				}
				
		        MvcResult obj;
		        
		        try{
		        	
		        	
		        	obj= mvc.perform(post(RestConstants.FIELD_HOME+RestConstants.ADD)        
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(toJson(fieldModel)))
		            .andExpect(status().isOk()).andReturn();
		     
		        	}catch (Exception e) {
				
					assertEquals(false,true);
					}
		      }
		  
		   private String toJson(FieldModel fieldModel) {
		    	
		    	try {
		             return new ObjectMapper().writeValueAsString(fieldModel);
		    		} catch (Exception e) {
		             throw new RuntimeException(e);
		         }
		    	
		    }
		  
		   /**
			   * <h1>findServiceByIdTest</h1>
			   * <p>Find Parameter Values in the Get Request. This will find  the values in the database by Service Id </p>
			   * @since 1.0.0.0
			   */
		   
		   @Disabled
		   @Test
		   @DisplayName("Find Service By Id Test One")
		   @Order(2)
		   public void findServiceByIdTest_One()  {

			
				
				MvcResult obj;
				try{
					
					
					obj=mvc.perform(get(RestConstants.SERVICE_HOME+"/1")		
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk()).andReturn();
								
				
				JSONObject jsonObj = new JSONObject(obj.getResponse().getContentAsString());
				

				JSONObject jsonObj1 = jsonObj.getJSONObject("object");
				
				JSONArray jsonArray1 = jsonObj1.getJSONArray("sfDtos");
				JSONObject jsonObj2 = (JSONObject) jsonArray1.get(0);
				
				//then
				
				assertEquals(5,jsonObj1.length());
				assertEquals("1",jsonObj1.get("serviceId").toString());
				assertEquals("serviceFieldDtoadded",jsonObj1.get("description").toString());
				assertEquals("check",jsonObj1.get("name").toString());
				assertEquals("1",jsonObj1.get("logoId").toString());
				assertEquals("1",jsonObj2.get("sfId").toString());
				assertEquals("1",jsonObj2.get("fieldId").toString());
				assertEquals("true",jsonObj2.get("mandatory").toString());	
			
				}catch (Exception e) {
					
					assertEquals(false,true);

				}
			}
		   
		   /**
		    * <h1>SaveServiceTest</h1>
		    * <p>Add Parameter Values in the Post Request.In this test the Data is send and is validated and then added to the database</p>
		    *@since 1.0.0.0
		    * 
		    */
		   
		   @Disabled
		   @Test
		   @DisplayName("Save Service Test 2")
		   @Order(3)
		   public void saveserviceTest_2_ITest() {

		    	List<ServiceModel> serviceModels = new ArrayList<ServiceModel>();
				ServiceModel serviceModel = new ServiceModel();
		        
				List<ServiceFieldDTO> serviceFieldDTOs = new ArrayList<ServiceFieldDTO>();  
				
				ServiceFieldDTO serviceFieldDTO = new ServiceFieldDTO();
				{
				
				serviceFieldDTO.setFieldId(2);
				serviceFieldDTO.setSfId(1);
				serviceFieldDTO.setMandatory(false);
				
				}
				
				serviceFieldDTOs.add(serviceFieldDTO);
				
				{	
					serviceModel.setSfDtos(serviceFieldDTOs);
		        	serviceModel.setLogoId(2);
					serviceModel.setDescription("serviceFieldDtoadded2");
					serviceModel.setServiceId(1);
					serviceModel.setName("check2");
					serviceModels.add(serviceModel);

				}
		      
		        MvcResult obj;
		      
		        try{
		        	
		        	
		        	obj= mvc.perform(post(RestConstants.SERVICE_HOME+RestConstants.ADD)        
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(toJson(serviceModel)))
		            .andExpect(status().isOk()).andReturn();
		        	
		        	  System.out.println("obj++++++++"+obj.getResponse().getContentAsString());
		        	}catch (Exception e) {
				
					assertEquals(false,true);
					}
		      }
	   
			/**
			   * <h1>findServiceByIdTest</h1>
			   * <p>Find Parameter Values in the Get Request. This will find  the values in the database by Service Id </p>
			   * @since 1.0.0.0
			   */
		   
		   @Disabled
		   @Test
		   @DisplayName("Find Service By Id Test")
		   @Order(4)
		   public void findServiceById_2_Test() throws Exception {

			
				
//				MvcResult obj;
//				try{
					
					
				
				MvcResult	obj=mvc.perform(get(RestConstants.SERVICE_HOME+"/2")		
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andReturn();
							
			
					JSONObject jsonObj = new JSONObject(obj.getResponse().getContentAsString());
			

					JSONObject jsonObj1 = jsonObj.getJSONObject("object");
			
					JSONArray jsonArray1 = jsonObj1.getJSONArray("sfDtos");
					JSONObject jsonObj2 = (JSONObject) jsonArray1.get(0);
				//then
				
				assertEquals(5,jsonObj1.length());
				assertEquals("2",jsonObj1.get("serviceId").toString());
				assertEquals("serviceFieldDtoadded2",jsonObj1.get("description").toString());
				assertEquals("check2",jsonObj1.get("name").toString());
				assertEquals("2",jsonObj1.get("logoId").toString());
				assertEquals("2",jsonObj2.get("sfId").toString());
				assertEquals("2",jsonObj2.get("fieldId").toString());
				assertEquals("false",jsonObj2.get("mandatory").toString());	
			
//				}catch (Exception e) {
//					
//					assertEquals(false,true);
	//
//				}
			}
		   
		   /**
		     * <h1>findServicesTest</h1>
		     * <p>Find Parameter Values in the Get Request. This will find all the values in the database</p>
		     * @since 1.0.0.0
		     */
		   
		   @Disabled
		   @Test
		   @DisplayName("find all Service Test 1")
		   @Order(5)
		   public void findAllServices_Test_1_IT(){

				MvcResult obj;
			
				try	{
			
	             	obj=mvc.perform(get(RestConstants.SERVICE_HOME+RestConstants.ALL)
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk()).andReturn();
				
				
				JSONObject jsonObj = new JSONObject(obj.getResponse().getContentAsString());			
				JSONArray jsonArray = jsonObj.getJSONArray("object");						
				
				JSONObject jsonObj1 = (JSONObject) jsonArray.get(0);			
				JSONObject jsonObj2 = (JSONObject) jsonArray.get(1);
				
				JSONArray jsonArray1 = jsonObj1.getJSONArray("sfDtos");
				JSONArray jsonArray2 = jsonObj2.getJSONArray("sfDtos");
				JSONObject jsonObj3 = (JSONObject) jsonArray1.get(0);
				JSONObject jsonObj4 = (JSONObject) jsonArray2.get(0);
							
				
				assertEquals("1",jsonObj1.get("serviceId").toString());
				assertEquals("serviceFieldDtoadded",jsonObj1.get("description").toString());
				assertEquals("check",jsonObj1.get("name").toString());
				assertEquals("1",jsonObj1.get("logoId").toString());
				assertEquals("1",jsonObj3.get("sfId").toString());
				assertEquals("1",jsonObj3.get("fieldId").toString());
				assertEquals("true",jsonObj3.get("mandatory").toString());	
				
				assertEquals("2",jsonObj2.get("serviceId").toString());
				assertEquals("serviceFieldDtoadded2",jsonObj2.get("description").toString());
				assertEquals("check2",jsonObj2.get("name").toString());
				assertEquals("2",jsonObj2.get("logoId").toString());
				assertEquals("2",jsonObj4.get("sfId").toString());
				assertEquals("2",jsonObj4.get("fieldId").toString());
				assertEquals("false",jsonObj4.get("mandatory").toString());
				
				}
						catch(Exception e){
				
								assertEquals(true,false);
				
							}
			}
		   
		   /**
		     * <h1> updateServiceTest </h1>
		     * <p>Update Parameter Values in the Put Request. This will Update the values in the database throw Id</p>
		     * @since 1.0.0.0
		     */
		   
		   @Disabled
		   @Test
		   @DisplayName("Update Service Test")
		   @Order(6)
		   public void updateServiceTest()  {
		   		
		   		List<ServiceModel> serviceModels = new ArrayList<ServiceModel>();
				ServiceModel serviceModel = new ServiceModel();
		        
				List<ServiceFieldDTO> serviceFieldDTOs = new ArrayList<ServiceFieldDTO>();  
				
				ServiceFieldDTO serviceFieldDTO = new ServiceFieldDTO();
				{
				
				serviceFieldDTO.setFieldId(3);
				serviceFieldDTO.setSfId(1);
				serviceFieldDTO.setMandatory(false);
				
				}
				
				serviceFieldDTOs.add(serviceFieldDTO);
				
				{	
					serviceModel.setSfDtos(serviceFieldDTOs);
		        	serviceModel.setLogoId(2);
					serviceModel.setDescription("serviceFieldDtoupdate");
					serviceModel.setServiceId(1);
					serviceModel.setName("checkupdate");
					serviceModels.add(serviceModel);

				}  
				
		        MvcResult obj;
		        try{
		         obj= mvc.perform(put(RestConstants.SERVICE_HOME+"/1")        
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(toJson(serviceModel)))
		            .andExpect(status().isOk()).andReturn();
		       
		       
		        }catch (Exception e) {
					
						assertEquals(false,true);
						
		        	}
		               	
		      }

		   	 /**
			   * <h1>findServiceByIdTest</h1>
			   * <p>Find Parameter Values in the Get Request. This will find  the values in the database by Service Id </p>
			   * @since 1.0.0.0
			   */
		   
		   @Disabled
		   @Test
		   @DisplayName("Find Service By Id Test")
		   @Order(7)
		   public void findServiceById_3_Test()  {

				
					
					MvcResult obj;
					try{
						
						
						obj=mvc.perform(get(RestConstants.SERVICE_HOME+"/1")		
							.contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk()).andReturn();
									
					
					JSONObject jsonObj = new JSONObject(obj.getResponse().getContentAsString());
					

					JSONObject jsonObj1 = jsonObj.getJSONObject("object");
					
					JSONArray jsonArray1 = jsonObj1.getJSONArray("sfDtos");
					JSONObject jsonObj2 = (JSONObject) jsonArray1.get(0);
					
					//then
					
					assertEquals(5,jsonObj1.length());
					assertEquals("17",jsonObj1.get("serviceId").toString());
					assertEquals("serviceFieldDtoupdate",jsonObj1.get("description").toString());
					assertEquals("checkupdate",jsonObj1.get("name").toString());
					assertEquals("2",jsonObj1.get("logoId").toString());
					assertEquals("",jsonObj2.get("sfId").toString());
					assertEquals("2",jsonObj2.get("fieldId").toString());
					assertEquals("false",jsonObj2.get("mandatory").toString());	
				
					}catch (Exception e) {
						
						assertEquals(false,true);

					}
				}	   	   
		   
		   /**
		     * <h1> updateServiceTest </h1>
		     * <p>Update Parameter Values in the Put Request. This will Update the values in the database throw Id</p>
		     * @since 1.0.0.0
		     */
		   
		   @Disabled
		   @Test
		   @DisplayName("Update Service Test")
		   @Order(8)
		   public void updateServiceTest_Addnewfield()  {
		   		
		   		List<ServiceModel> serviceModels = new ArrayList<ServiceModel>();
				ServiceModel serviceModel = new ServiceModel();
		        
				List<ServiceFieldDTO> serviceFieldDTOs = new ArrayList<ServiceFieldDTO>();  
				
				ServiceFieldDTO serviceFieldDTO = new ServiceFieldDTO();
				{
				
				serviceFieldDTO.setFieldId(4);
				serviceFieldDTO.setSfId(1);
				serviceFieldDTO.setMandatory(false);
				
				}
				
				serviceFieldDTOs.add(serviceFieldDTO);
				
				{	
					serviceModel.setSfDtos(serviceFieldDTOs);
		        	serviceModel.setLogoId(1);
					serviceModel.setDescription("serviceFieldDtoupdateaddfield");
					serviceModel.setServiceId(17);
					serviceModel.setName("checkupdateaddfield");
					serviceModels.add(serviceModel);

				}  
				
		        MvcResult obj;
		        try{
		         obj= mvc.perform(put(RestConstants.SERVICE_HOME+"/17")        
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(toJson(serviceModel)))
		            .andExpect(status().isOk()).andReturn();
		        System.out.println("obj++++++++"+obj.getResponse().getContentAsString());
		       
		        }catch (Exception e) {
					
						assertEquals(false,true);
						
		        	}
		               	
		      }

			 /**
			   * <h1>findServiceByIdTest</h1>
			   * <p>Find Parameter Values in the Get Request. This will find  the values in the database by Service Id </p>
			   * @since 1.0.0.0
			   */   
		   
		   @Disabled
		   @Test
		   @DisplayName("Find Service By Id Test")
		   @Order(9)
		   public void findServiceById_4_Test()  {

				
					
					MvcResult obj;
					try{
						
						
						obj=mvc.perform(get(RestConstants.SERVICE_HOME+"/1")		
							.contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk()).andReturn();
									
					
					JSONObject jsonObj = new JSONObject(obj.getResponse().getContentAsString());
					

					JSONObject jsonObj1 = jsonObj.getJSONObject("object");
					
					JSONArray jsonArray1 = jsonObj1.getJSONArray("sfDtos");
					JSONObject jsonObj2 = (JSONObject) jsonArray1.get(0);
					
					//then
					
					assertEquals(5,jsonObj1.length());
					assertEquals("17",jsonObj1.get("serviceId").toString());
					assertEquals("serviceFieldDtoupdateaddfield",jsonObj1.get("description").toString());
					assertEquals("checkupdateaddfield",jsonObj1.get("name").toString());
					assertEquals("1",jsonObj1.get("logoId").toString());
					assertEquals("7",jsonObj2.get("sfId").toString());
					assertEquals("4",jsonObj2.get("fieldId").toString());
					assertEquals("false",jsonObj2.get("mandatory").toString());	
				
					}catch (Exception e) {
						
						assertEquals(false,true);

					}
				}	   	   
		   
		   	
		   /**
		     * <h1>findServicesTest</h1>
		     * <p>Find Parameter Values in the Get Request. This will find all the values in the database</p>
		     * @since 1.0.0.0
		     */
		   
		   	@Disabled
		    @Test
		    @DisplayName("find all  Service Test 2")
			@Order(10)
			public void findAllServices_Test_2_IT() {

					MvcResult obj;
				
					try	{
				
						obj=mvc.perform(get(RestConstants.SERVICE_HOME+RestConstants.ALL)
							.contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk()).andReturn();
				
					
					JSONObject jsonObj = new JSONObject(obj.getResponse().getContentAsString());			
					JSONArray jsonArray = jsonObj.getJSONArray("object");						
					
					JSONObject jsonObj1 = (JSONObject) jsonArray.get(0);			
					JSONObject jsonObj2 = (JSONObject) jsonArray.get(1);
					
					JSONArray jsonArray1 = jsonObj1.getJSONArray("sfDtos");
					JSONArray jsonArray2 = jsonObj2.getJSONArray("sfDtos");
					JSONObject jsonObj3 = (JSONObject) jsonArray1.get(0);
					JSONObject jsonObj4 = (JSONObject) jsonArray2.get(0);
								
					
					assertEquals("17",jsonObj1.get("serviceId").toString());
					assertEquals("serviceFieldDtoadded",jsonObj1.get("description").toString());
					assertEquals("check",jsonObj1.get("name").toString());
					assertEquals("1",jsonObj1.get("logoId").toString());
					assertEquals("5",jsonObj3.get("sfId").toString());
					assertEquals("1",jsonObj3.get("fieldId").toString());
					assertEquals("true",jsonObj3.get("mandatory").toString());	
					
					assertEquals("18",jsonObj2.get("serviceId").toString());
					assertEquals("serviceFieldDtoadded2",jsonObj2.get("description").toString());
					assertEquals("check2",jsonObj2.get("name").toString());
					assertEquals("2",jsonObj2.get("logoId").toString());
					assertEquals("6",jsonObj4.get("sfId").toString());
					assertEquals("3",jsonObj4.get("fieldId").toString());
					assertEquals("true",jsonObj4.get("mandatory").toString());
					
					
					}
							catch(Exception e){
					
									assertEquals(true,false);
					
								}
				}	   	    	    	   	   
		   
		    
		   	
		   	@Disabled
		    @Test
			@DisplayName("Delete Service By Id Test ")
		    @Order(11)
			public void deleteServiceByIdTest()  {
			
				MvcResult obj;
				try {
				 obj=mvc.perform(delete(RestConstants.SERVICE_HOME+"/12")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk()).andReturn();
				
				String path=obj.getRequest().getPathInfo();

		        String[] arrOfStr = path.split("/", -2); 
		        
		        String param=arrOfStr[arrOfStr.length -1];

		        //then
		        
		        assertEquals(true,isInteger(param)&&nonNegative(param)&&isId(param));
					} catch (Exception e) {
		    		
		    			assertEquals(false,true);
		    		
						}
			}
		    
		   	/**
		     * <h1>findServicesTest</h1>
		     * <p>Find Parameter Values in the Get Request. This will find all the values in the database</p>
		     * @since 1.0.0.0
		     */
		    
		   	@Disabled
		    @Test
		    @DisplayName("find all  Service Test 3")
			@Order(12)
			public void findAllServices_Test_3_IT() {

					MvcResult obj;
				
					try	{
				
						obj=mvc.perform(get(RestConstants.SERVICE_HOME+RestConstants.ALL)
							.contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk()).andReturn();
						JSONObject jsonObj = new JSONObject(obj.getResponse().getContentAsString());			
						JSONArray jsonArray = jsonObj.getJSONArray("object");						
						
						JSONObject jsonObj1 = (JSONObject) jsonArray.get(0);			
						JSONObject jsonObj2 = (JSONObject) jsonArray.get(1);
						
						JSONArray jsonArray1 = jsonObj1.getJSONArray("sfDtos");
						JSONArray jsonArray2 = jsonObj2.getJSONArray("sfDtos");
						JSONObject jsonObj3 = (JSONObject) jsonArray1.get(0);
						JSONObject jsonObj4 = (JSONObject) jsonArray2.get(0);
									
						
						assertEquals("17",jsonObj1.get("serviceId").toString());
						assertEquals("serviceFieldDtoadded",jsonObj1.get("description").toString());
						assertEquals("check",jsonObj1.get("name").toString());
						assertEquals("1",jsonObj1.get("logoId").toString());
						assertEquals("5",jsonObj3.get("sfId").toString());
						assertEquals("1",jsonObj3.get("fieldId").toString());
						assertEquals("true",jsonObj3.get("mandatory").toString());	
						
						assertEquals("18",jsonObj2.get("serviceId").toString());
						assertEquals("serviceFieldDtoadded2",jsonObj2.get("description").toString());
						assertEquals("check2",jsonObj2.get("name").toString());
						assertEquals("2",jsonObj2.get("logoId").toString());
						assertEquals("6",jsonObj4.get("sfId").toString());
						assertEquals("3",jsonObj4.get("fieldId").toString());
						assertEquals("true",jsonObj4.get("mandatory").toString());
						
					
					}
							catch(Exception e){
					
									assertEquals(true,false);
					
								}
				}
		    
		    @Disabled
		    @Test
			@DisplayName("Delete Service By SFId Test ")
		    @Order(13)
			public void deleteServiceBySfIdTest()  {
			
				MvcResult obj;
				try {
				 obj=mvc.perform(delete(RestConstants.SERVICE_HOME+"/17")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk()).andReturn();
				
				String path=obj.getRequest().getPathInfo();

		        String[] arrOfStr = path.split("/", -2); 
		        
		        String param=arrOfStr[arrOfStr.length -1];

		        //then
		        
		        assertEquals(true,isInteger(param)&&nonNegative(param)&&isId(param));
					} catch (Exception e) {
		    		
		    			assertEquals(false,true);
		    		
						}
			}
		    
		    @Disabled
		    @Test
		    @DisplayName("find all  Service Test 3")
			@Order(14)
			public void findAllServices_Test_4_IT() {

					MvcResult obj;
				
					try	{
				
						obj=mvc.perform(get(RestConstants.SERVICE_HOME+RestConstants.ALL)
							.contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk()).andReturn();
						JSONObject jsonObj = new JSONObject(obj.getResponse().getContentAsString());			
						JSONArray jsonArray = jsonObj.getJSONArray("object");						
						
						JSONObject jsonObj1 = (JSONObject) jsonArray.get(0);			
						JSONObject jsonObj2 = (JSONObject) jsonArray.get(1);
						
						JSONArray jsonArray1 = jsonObj1.getJSONArray("sfDtos");
						JSONArray jsonArray2 = jsonObj2.getJSONArray("sfDtos");
						JSONObject jsonObj3 = (JSONObject) jsonArray1.get(0);
						JSONObject jsonObj4 = (JSONObject) jsonArray2.get(0);
									
						
						assertEquals("17",jsonObj1.get("serviceId").toString());
						assertEquals("serviceFieldDtoadded",jsonObj1.get("description").toString());
						assertEquals("check",jsonObj1.get("name").toString());
						assertEquals("1",jsonObj1.get("logoId").toString());
						assertEquals("5",jsonObj3.get("sfId").toString());
						assertEquals("1",jsonObj3.get("fieldId").toString());
						assertEquals("true",jsonObj3.get("mandatory").toString());	
						
						assertEquals("18",jsonObj2.get("serviceId").toString());
						assertEquals("serviceFieldDtoadded2",jsonObj2.get("description").toString());
						assertEquals("check2",jsonObj2.get("name").toString());
						assertEquals("2",jsonObj2.get("logoId").toString());
						assertEquals("6",jsonObj4.get("sfId").toString());
						assertEquals("3",jsonObj4.get("fieldId").toString());
						assertEquals("true",jsonObj4.get("mandatory").toString());
						
					
					}
							catch(Exception e){
					
									assertEquals(true,false);
					
								}
				}
		    
		    /**
			   * <h1>deleteAllServicesTest</h1>
			   * <p>delete Parameter Values in the delete Request. This will delete all the values in the database </p>
			   * @since 1.0.0.0
			   */
		    
		    @Disabled
			@Test
			@DisplayName("Delete All Services Test ")
			@Order(15)
			public void deleteAllServicesTest() {
				
				MvcResult obj;
				try {
				
					 obj = mvc.perform(delete(RestConstants.SERVICE_HOME+RestConstants.ALL)
							.contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk()).andReturn();									
					}	catch(Exception e){
					assertEquals(false,true);
				}				
								
			}
			
		    @Disabled
			@Test
			@DisplayName("Find Services Test  Model is Empty")
			@Order(16)
			public void findServicesTest_isEmpty()  {
			
				MvcResult obj;
				try{
				obj=mvc.perform(get(RestConstants.SERVICE_HOME+RestConstants.ALL)
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk()).andReturn();;
				
						assertEquals(0,obj.getResponse().getContentLength());
						
					} catch (Exception e) {
				
					assertEquals(false,true);

						}

			}
			
		//Function 
		
	    public boolean isInteger( String input ) {
	        try { 
	            Integer.parseInt( input );
	            return true;
	        }
	        catch( Exception e ) { 
	            return false;
	        }
	    } 
		
		public boolean nonNegative(String input)
		{
			 try { 
		           int num= Integer.parseInt( input );
		           if(num>=0)
		            return true;
		           else
		        	   return false;
		        }
		        catch( Exception e ) { 
		            return false;
		        }
		}
		
		public boolean isId(String input)
		{
			 try { 
		           int num= Integer.parseInt( input );
		           if(num>0)
		            return true;
		           else
		        	   return false;
		        }
		        catch( Exception e ) { 
		            return false;
		        }
			
		}
		
		public boolean isBoolean(String input)
		{
			if(input.equalsIgnoreCase("true")||input.equalsIgnoreCase("true")) {
				return true;
			}
			return false;
		}

	}

