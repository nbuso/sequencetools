/*******************************************************************************
 * Copyright 2012-2013 EMBL-EBI, Hinxton outstation
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
package uk.ac.ebi.embl.api.validation.fixer.sourcefeature;

import uk.ac.ebi.embl.api.entry.feature.Feature;
import uk.ac.ebi.embl.api.entry.feature.SourceFeature;
import uk.ac.ebi.embl.api.entry.qualifier.Qualifier;
import uk.ac.ebi.embl.api.validation.Severity;
import uk.ac.ebi.embl.api.validation.ValidationResult;
import uk.ac.ebi.embl.api.validation.annotation.Description;
import uk.ac.ebi.embl.api.validation.annotation.RemoteExclude;
import uk.ac.ebi.embl.api.validation.check.feature.FeatureValidationCheck;
import uk.ac.ebi.ena.taxonomy.taxon.Taxon;

import java.util.List;

@Description("\"{0}\" qualifier value has been changed to scientific name \"{1}\"")
@RemoteExclude
public class HostQualifierFix extends FeatureValidationCheck
{
	private final static String HOST_QUALIFIER_VALUE_FIX_ID = "HostQualifierFix_1";
	
	public ValidationResult check(Feature feature)
	{
		result = new ValidationResult();

		if(feature==null)
		{
			return result;
		}
		
		if(!(feature instanceof SourceFeature))
			return result;
		
		SourceFeature source=(SourceFeature)feature;
		List<Qualifier> hostQualifiers = source.getQualifiers(Qualifier.HOST_QUALIFIER_NAME);
		for (Qualifier hostQualifier : hostQualifiers)
		{
			String hostQualifierValue = hostQualifier.getValue();
			
			if(getEmblEntryValidationPlanProperty().taxonHelper.get().isOrganismValid(hostQualifierValue))
				continue;
			
			List<Taxon> taxon = getEmblEntryValidationPlanProperty().taxonHelper.get().getTaxonsByCommonName(hostQualifierValue);

			if(taxon==null||taxon.size()==0)
			{
				continue;
			}
			else if(taxon.get(0).getScientificName()!=null)
			{
				hostQualifier.setValue(taxon.get(0).getScientificName());
				reportMessage(Severity.FIX, hostQualifier.getOrigin(), HOST_QUALIFIER_VALUE_FIX_ID, Qualifier.HOST_QUALIFIER_NAME,hostQualifier.getValue());
			}
		}

		return result;

	}

}
