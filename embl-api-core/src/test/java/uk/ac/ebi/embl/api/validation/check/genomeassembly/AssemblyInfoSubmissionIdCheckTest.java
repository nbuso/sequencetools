/*******************************************************************************
 * Copyright 2012 EMBL-EBI, Hinxton outstation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package uk.ac.ebi.embl.api.validation.check.genomeassembly;

import uk.ac.ebi.embl.api.entry.EntryFactory;
import uk.ac.ebi.embl.api.entry.feature.FeatureFactory;
import uk.ac.ebi.embl.api.entry.genomeassembly.AssemblyInfoEntry;
import uk.ac.ebi.embl.api.entry.qualifier.Qualifier;
import uk.ac.ebi.embl.api.entry.qualifier.QualifierFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import uk.ac.ebi.embl.api.storage.DataRow;
import uk.ac.ebi.embl.api.storage.DataSet;
import uk.ac.ebi.embl.api.validation.Severity;
import uk.ac.ebi.embl.api.validation.ValidationEngineException;
import uk.ac.ebi.embl.api.validation.ValidationMessageManager;
import uk.ac.ebi.embl.api.validation.ValidationResult;
import uk.ac.ebi.embl.api.validation.ValidationScope;
import uk.ac.ebi.embl.api.validation.dao.EntryDAOUtils;
import uk.ac.ebi.embl.api.validation.fixer.sourcefeature.AssemblySourceQualiferFix;
import uk.ac.ebi.embl.api.validation.plan.EmblEntryValidationPlanProperty;
import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AssemblyInfoSubmissionIdCheckTest
{
//	private AssemblyInfoEntry assemblyEntry;
//	private AssemblyInfoValidationheck check;
//	private EmblEntryValidationPlanProperty planProperty;
//
//	@Before
//	public void setUp() throws SQLException
//	{
//		check = new AssemblyInfoValidationheck();
//		ValidationMessageManager.addBundle(ValidationMessageManager.STANDARD_FIXER_BUNDLE);
//        EntryDAOUtils entryDAOUtils = createMock(EntryDAOUtils.class);
//		planProperty=new EmblEntryValidationPlanProperty();
//		planProperty.analysis_id.set("ERZ0001");
//		check = new AssemblyInfoValidationheck();
//		check.setEmblEntryValidationPlanProperty(planProperty);
//	}
//
//	@Test
//	public void testCheck_NoEntry() throws ValidationEngineException
//	{
//		assertTrue(check.check(null).isValid());
//	}
//
//	@Test
//	public void testCheck_NoMandatory() throws ValidationEngineException
//	{
//		ValidationResult result = check.check(assemblyEntry);
//		assertEquals(1, result.count("AssemblyFieldandValueCheck-2", Severity.ERROR));
//	}
//
//	@Test
//	public void testCheck_InvalidField() throws ValidationEngineException
//	{
//		Field field2 = new Field("assbly_name", "cb4");
//		gaRecord.addField(field2);
//		ValidationResult result = check.check(gaRecord);
//		assertEquals(1, result.count("AssemblyFieldandValueCheck-1", Severity.ERROR));
//	}
//
//	@Test
//	public void testCheck_InvalidFinishing_goal() throws ValidationEngineException
//	{
//		Field field2 = new Field("finishing_goal", "fgfhh");
//		gaRecord.addField(field2);
//		ValidationResult result = check.check(gaRecord);
//		assertEquals(0, result.count("AssemblyFieldandValueCheck-3", Severity.ERROR));
//	}
//
//	@Test
//	@Ignore
//	public void testCheck_validRecord() throws SQLException, ValidationEngineException
//	{
//		Field field2 = new Field("project", "PRJEA84437");
//		gaRecord.addField(field2);
//		assertTrue(check.check(gaRecord).isValid());
//
//	}
//
//	@Test
//	public void testCheck_InvalidReleaseDate() throws ValidationEngineException
//	{
//		Field field2 = new Field("release_date", "12-MON-34");
//		gaRecord.addField(field2);
//		ValidationResult result = check.check(gaRecord);
//		assertEquals(1, result.count("AssemblyFieldandValueCheck-3", Severity.ERROR));
//	}
//
//	@Test
//	public void testCheck_InvalidVersion() throws ValidationEngineException
//	{
//		Field field2 = new Field("assembly_version", "er");
//		gaRecord.addField(field2);
//		ValidationResult result = check.check(gaRecord);
//		assertEquals(0, result.count("AssemblyFieldandValueCheck-3", Severity.ERROR));
//	}

}
