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

import org.opentdc.service.LocalizedTextModel;
import org.opentdc.service.exception.DuplicateException;
import org.opentdc.service.exception.InternalServerErrorException;
import org.opentdc.service.exception.NotFoundException;
import org.opentdc.service.exception.ValidationException;

public interface ServiceProvider {
	
	public abstract List<TagTextModel> list(
		String queryType,
		String query,
		int position,
		int size
	);

	public abstract TagModel create(
		TagModel tag) 
	throws DuplicateException, ValidationException;

	public abstract TagModel read(
		String id) 
	throws NotFoundException;

	public abstract TagModel update(
		String id, 
		TagModel tag) 
	throws NotFoundException, ValidationException;

	public abstract void delete(
		String id) 
	throws NotFoundException, InternalServerErrorException;
	
	//**************************** localized text *************************************
	public abstract List<LocalizedTextModel> listTexts(
			String tid,
			String queryType,
			String query,
			int position,
			int size
		);

	public abstract LocalizedTextModel createText(
			String tid,
			LocalizedTextModel text) 
	throws DuplicateException, ValidationException;

	public abstract LocalizedTextModel readText(
			String tid,
			String lid) 
	throws NotFoundException;

	public abstract LocalizedTextModel updateText(
			String tid,
			String lid, 
			LocalizedTextModel text) 
	throws NotFoundException, ValidationException;

	public abstract void deleteText(
			String tid,
			String lid) 
	throws NotFoundException, InternalServerErrorException;
}
