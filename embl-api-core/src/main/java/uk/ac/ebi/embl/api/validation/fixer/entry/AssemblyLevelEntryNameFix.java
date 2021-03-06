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
package uk.ac.ebi.embl.api.validation.fixer.entry;

import uk.ac.ebi.embl.api.entry.Entry;
import uk.ac.ebi.embl.api.validation.Severity;
import uk.ac.ebi.embl.api.validation.ValidationEngineException;
import uk.ac.ebi.embl.api.validation.ValidationResult;
import uk.ac.ebi.embl.api.validation.ValidationScope;
import uk.ac.ebi.embl.api.validation.annotation.Description;
import uk.ac.ebi.embl.api.validation.annotation.ExcludeScope;
import uk.ac.ebi.embl.api.validation.annotation.GroupIncludeScope;
import uk.ac.ebi.embl.api.validation.check.entry.EntryValidationCheck;

@Description("EntryName is missing for a {0}, assigned new entryName : {1}")
@GroupIncludeScope(group = { ValidationScope.Group.ASSEMBLY })
@ExcludeScope(validationScope={ValidationScope.ASSEMBLY_MASTER})
public class AssemblyLevelEntryNameFix extends EntryValidationCheck
{
	private final String ENTRYNAME_FIX_ID = "AssemblyLevelEntryNameFix";
	
	private int assemblySeqNumber=0;

	public void setAssemblySeqNumber(int assemblySeqNumber)
	{
		this.assemblySeqNumber = assemblySeqNumber;
	}
	
	public ValidationResult check(Entry entry) throws ValidationEngineException
	{
		result = new ValidationResult();

		if (entry == null||assemblySeqNumber==0)
		{
			return result;
		}

		String seqType = ValidationScope.ASSEMBLY_CONTIG.equals(getEmblEntryValidationPlanProperty().validationScope.get()) ? "contig" : ValidationScope.ASSEMBLY_SCAFFOLD.equals(getEmblEntryValidationPlanProperty().validationScope.get()) ? "scaffold" : ValidationScope.ASSEMBLY_CHROMOSOME.equals(getEmblEntryValidationPlanProperty().validationScope.get()) ? "chromosome":"entry";
		String entryName= seqType+ assemblySeqNumber;
		
		if(entry.getSubmitterAccession()==null)
		{
			entry.setSubmitterAccession(entryName);
			reportMessage(Severity.FIX, entry.getOrigin(), ENTRYNAME_FIX_ID ,seqType, entryName);
		}

		return result;
	}

}
