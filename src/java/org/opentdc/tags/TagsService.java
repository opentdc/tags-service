/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Arbalo AG
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.opentdc.tags;

import java.util.List;
import java.util.logging.Logger;



// import io.swagger.annotations.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.opentdc.service.GenericService;
import org.opentdc.service.LocalizedTextModel;
import org.opentdc.service.exception.DuplicateException;
import org.opentdc.service.exception.InternalServerErrorException;
import org.opentdc.service.exception.NotFoundException;
import org.opentdc.service.exception.ValidationException;

@Path("/api/tag")
// @Api(value = "/api/tag", description = "Operations about tags")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagsService extends GenericService<ServiceProvider> {
	
	private static ServiceProvider sp = null;

	private static final Logger logger = Logger.getLogger(TagsService.class.getName());

	/**
	 * Invoked for each service invocation (Constructor)
	 * @throws ReflectiveOperationException
	 */
	public TagsService(
		@Context ServletContext context
	) throws ReflectiveOperationException{
		logger.info("> TagsService()");
		if (sp == null) {
			sp = this.getServiceProvider(TagsService.class, context);
		}
		logger.info("TagsService() initialized");
	}

	/******************************** tag *****************************************/	
	@GET
	@Path("/")
//	@ApiOperation(value = "Return a list of all tags", response = List<TagModel>.class)
	public List<SingleLangTag> list(
		@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
		@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
		@DefaultValue(DEFAULT_POSITION) @QueryParam("position") int position,
		@DefaultValue(DEFAULT_SIZE) @QueryParam("size") int size
	) {
		return sp.list(query, queryType, position, size);
	}
	
	@POST
	@Path("/")
	//	@ApiOperation(value = "Create a new tag", response = TagModel.class)
	//	@ApiResponses(value = 
	//			{ @ApiResponse(code = 409, message = "An object with the same id exists already (CONFLICT)") },
	//			{ @ApiResponse(code = 400, message = "Invalid ID supplied or mandatory field missing (BAD_REQUEST)" })
	public TagModel create(
		@Context HttpServletRequest request,
		TagModel tag) 
	throws DuplicateException, ValidationException {
		return sp.create(request, tag);
	}

	@GET
	@Path("/{id}")
	//	@ApiOperation(value = "Find a tag by id", response = TagModel.class)
	//	@ApiResponses(value = { 
	//			@ApiResponse(code = 405, message = "An object with the given id was not found (NOT_FOUND)" })
	public TagModel read(
		@PathParam("id") String id
	) throws NotFoundException {
		return sp.read(id);
	}

	@PUT
	@Path("/{id}")
	//	@ApiOperation(value = "Update the tag with id with new values", response = TagModel.class)
	//	@ApiResponses(value =  
	//			{ @ApiResponse(code = 405, message = "An object with the given id was not found (NOT_FOUND)" },
	//			{ @ApiResponse(code = 400, message = "Invalid new values given or trying to change immutable fields (BAD_REQUEST)" })
	public TagModel update(
		@Context HttpServletRequest request,
		@PathParam("id") String id,
		TagModel tag
	) throws NotFoundException, ValidationException {
		return sp.update(request, id, tag);
	}

	@DELETE
	@Path("/{id}")
	//	@ApiOperation(value = "Delete the tag with the given id" )
	//	@ApiResponses(value =  
	//			{ @ApiResponse(code = 405, message = "An object with the given id was not found (NOT_FOUND)" },
	//			{ @ApiResponse(code = 500, message = "Data inconsistency found (INTERNAL_SERVER_ERROR)" })
	public void delete(
		@PathParam("id") String id) 
	throws NotFoundException, InternalServerErrorException {
		sp.delete(id);
	}
	
	/********************************** lang ***************************************/
	@GET
	@Path("/{tid}/lang")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LocalizedTextModel> listTexts(
		@PathParam("tid") String tid,
		@DefaultValue(DEFAULT_QUERY) @QueryParam("query") String query,
		@DefaultValue(DEFAULT_QUERY_TYPE) @QueryParam("queryType") String queryType,
		@DefaultValue(DEFAULT_POSITION) @QueryParam("position") int position,
		@DefaultValue(DEFAULT_SIZE) @QueryParam("size") int size
	) {
		return sp.listTexts(tid, query, queryType, position, size);
	}

	@POST
	@Path("/{tid}/lang")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LocalizedTextModel createText(
		@Context HttpServletRequest request,
		@PathParam("tid") String tid, 
		LocalizedTextModel text
	) throws DuplicateException, ValidationException {
		return sp.createText(request, tid, text);
	}
	
	@GET
	@Path("/{tid}/lang/{lid}")
	@Produces(MediaType.APPLICATION_JSON)
	public LocalizedTextModel readText(
		@PathParam("tid") String tid,
		@PathParam("lid") String lid
	) throws NotFoundException {
		return sp.readText(tid, lid);
	}

	@PUT
	@Path("/{tid}/lang/{lid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LocalizedTextModel updateText(
		@Context HttpServletRequest request,
		@PathParam("tid") String tid,
		@PathParam("lid") String lid,
		LocalizedTextModel text
	) throws NotFoundException, ValidationException {
		return sp.updateText(request, tid, lid, text);
	}

	@DELETE
	@Path("/{tid}/lang/{lid}")
	public void deleteText(
		@PathParam("tid") String tid,
		@PathParam("lid") String lid
	) throws NotFoundException, InternalServerErrorException {
		sp.deleteText(tid, lid);
	}
}
