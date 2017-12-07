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

import uk.ac.ebi.embl.api.entry.genomeassembly.AssemblyInfoEntry;
import uk.ac.ebi.embl.api.validation.ValidationEngineException;
import uk.ac.ebi.embl.api.validation.ValidationResult;
import uk.ac.ebi.embl.api.validation.annotation.Description;
import uk.ac.ebi.embl.api.validation.helper.taxon.TaxonHelper;

@Description("")
public class AssemblyInfoOrganismCheck extends GenomeAssemblyValidationCheck<AssemblyInfoEntry>
{
   
	private final String MESSAGE_KEY_INVALID_ORGANISM_ERROR = "AssemblyInfoInvalidOrganismCheck";
	private final String MESSAGE_KEY_ORGANISM_MISSING_ERROR = "AssemblyInfoOrganismMissingCheck";
	
	@Override
	public ValidationResult check(AssemblyInfoEntry entry) throws ValidationEngineException
	{
		if(entry==null)
			return result;
		
		if(entry.getOrganism()==null)
		{
			reportError(entry.getOrigin(),MESSAGE_KEY_ORGANISM_MISSING_ERROR);
			return result;
		}
		
		TaxonHelper taxonHelper=getEmblEntryValidationPlanProperty().taxonHelper.get();
		if(!taxonHelper.isOrganismValid(entry.getOrganism()))
		{
			reportError(entry.getOrigin(),MESSAGE_KEY_INVALID_ORGANISM_ERROR);
		}
		return result;
	}
	
}
