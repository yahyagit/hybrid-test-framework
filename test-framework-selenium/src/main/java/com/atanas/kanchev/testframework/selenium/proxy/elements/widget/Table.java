/*
 * Copyright 2016 Atanas Stoychev Kanchev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atanas.kanchev.testframework.selenium.proxy.elements.widget;

import com.atanas.kanchev.testframework.selenium.proxy.elements.base.Element;
import com.atanas.kanchev.testframework.selenium.proxy.elements.base.ImplementedBy;
import org.openqa.selenium.WebElement;


/**
 * Table functionality.
 */
@ImplementedBy(TableImpl.class)
public interface Table extends Element {

	/**
     * Gets the number of rows in the table
     * @return int equal to the number of rows in the table
     */
    int getRowCount();

    /**
     * Gets the number of columns in the table
     * @return int equal to the number of rows in the table
     */
    int getColumnCount();

    /**
     * Gets the WebElement of the cell at the specified index
     * @param rowIdx The zero based index of the row
     * @param colIdx The zero based index of the column
     * @return the WebElement of the cell at the specified index
     */
    WebElement getCellAtIndex(int rowIdx, int colIdx);
}
