/*!
 *
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 *
 * Copyright (c) 2002-2024 Hitachi Vantara. All rights reserved.
 *
 */

package org.pentaho.platform.api.engine;

import static org.junit.jupiter.api.Assertions.fail;
import static org.pentaho.platform.api.test.ExceptionTester.hasValidExceptionConstructors;

import java.lang.reflect.Constructor;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.pentaho.actionsequence.dom.IActionDefinition;

/**
 * Created by bgroves on 11/9/15.
 */
public class ActionExecutionExceptionTest {

  @Test
  public void testExceptionClasses() {
    hasValidExceptionConstructors( ActionExecutionException.class );
  }

  @Test
  public void testCustomizedConstructors() {
    IActionDefinition actionDef = Mockito.mock( IActionDefinition.class );

    try {
      Constructor<ActionExecutionException> constructor = ActionExecutionException.class.getDeclaredConstructor( String.class, Throwable.class, String.class, String.class,
          String.class, IActionDefinition.class );
      constructor.setAccessible( true );
      constructor.newInstance( "msg", new Exception( "cause" ), "sessionName", "instanceId", "actionSequenceName", actionDef );
    } catch ( Exception e ) {
      fail( ActionExecutionException.class.getSimpleName() + " Does not have a constructor with String, Throwable, String, String, String, IActionDefinition " );
    }

    try {
      Constructor<ActionExecutionException> constructor = ActionExecutionException.class.getDeclaredConstructor( String.class, String.class, String.class,
          String.class, IActionDefinition.class );
      constructor.setAccessible( true );
      constructor.newInstance( "msg", "sessionName", "instanceId", "actionSequenceName", actionDef  );
    } catch ( Exception e ) {
      fail( ActionExecutionException.class.getSimpleName() + " Does not have a constructor with String, String, String, String, IActionDefinition " );
    }
  }

}
