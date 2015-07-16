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

import java.util.Comparator;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.opentdc.service.LocalizedTextModel;
import org.opentdc.util.LanguageCode;

/**
 * @author Bruno Kaiser
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class TagTextModel {
	private String tagId;
	private String localizedTextId;
	private String text;
	private LanguageCode lang;
	private String createdBy;
	private Date createdAt;

	/**
	 * Empty constructor.
	 */
	public TagTextModel() {
	}
	
	public TagTextModel(String tagId, LocalizedTextModel ltm, String principal) {
		this.tagId = tagId;
		this.localizedTextId = ltm.getId();
		this.text = ltm.getText();
		this.lang = ltm.getLangCode();
		createdBy = principal;
		createdAt = new Date();
	}
	
	/**
	 * Get the creation date.
	 * @return the creation date
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Set the creation date. This attibute should only be set once at creation time and exclusively by the server.
	 * @param createdAt the date when this object was created.
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/** 
	 * Get the creation date.
	 * @return the creation date
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Set the createdBy attribute, i.e. the user that created the attribute.
	 * This attribute should only be set once at creation time and exclusively by the server.
	 * @param createdBy  the loginId of the user having created the object
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	/**
	 * @return the id of the TagsModel object
	 */
	public String getTagId() {
		return tagId;
	}

	/**
	 * @param tagId the id of the TagsModel object
	 */
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	/**
	 * @return the id of the LocalizedText object
	 */
	public String getLocalizedTextId() {
		return localizedTextId;
	}

	/**
	 * @param localizedTextId the id of the LocalizedText object
	 */
	public void setLocalizedTextId(String localizedTextId) {
		this.localizedTextId = localizedTextId;
	}

	/**
	 * @return the language code
	 */
	public LanguageCode getLang() {
		return lang;
	}

	/**
	 * @param lang the language code
	 */
	public void setLang(LanguageCode lang) {
		this.lang = lang;
	}
	
	/**
	 * @return the text of the tag (one word)
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text of the tag (one word)
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/******************************* Comparator *****************************/
	/**
	 * Comparator for comparing two TagTextModel based on the tag text.
	 */
	public static Comparator<TagTextModel> TagComparator = new Comparator<TagTextModel>() {

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 * @return -1 if the first object is null of smaller; otherwise it returns +1
		 */
		public int compare(TagTextModel obj1, TagTextModel obj2) {
			if (obj1.getText() == null) {
				return -1;
			}
			if (obj2.getText() == null) {
				return 1;
			}

			String _attr1 = obj1.getText();
			String _attr2 = obj2.getText();

			// ascending order
			return _attr1.compareTo(_attr2);

			// descending order
			// return _attr2.compareTo(_attr1);
		}
	};
}
