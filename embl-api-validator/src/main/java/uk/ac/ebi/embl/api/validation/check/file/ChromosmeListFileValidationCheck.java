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
package uk.ac.ebi.embl.api.validation.check.file;

import java.util.List;
import uk.ac.ebi.embl.api.entry.genomeassembly.ChromosomeEntry;
import uk.ac.ebi.embl.api.validation.*;
import uk.ac.ebi.embl.api.validation.annotation.Description;
import uk.ac.ebi.embl.api.validation.plan.EmblEntryValidationPlanProperty;
import uk.ac.ebi.embl.api.validation.submission.SubmissionFile;
import uk.ac.ebi.embl.flatfile.reader.genomeassembly.ChromosomeListFileReader;

@Description("")
public class ChromosmeListFileValidationCheck extends FileValidationCheck
{

	public ChromosmeListFileValidationCheck(EmblEntryValidationPlanProperty property) 
	{
		super(property);
	}	
	@Override
	public boolean check(SubmissionFile submissionFile) throws ValidationEngineException
	{
		boolean valid =true;
		try
		{
		ChromosomeListFileReader reader = new ChromosomeListFileReader(submissionFile.getFile());
		ValidationResult parseResult = reader.read();
		if(!parseResult.isValid())
		{
			valid = false;
		}
		List<ChromosomeEntry> chromsomeEntries = reader.getentries();
		getChromosomeEntryNames(chromsomeEntries);
		
		}catch(Exception e)
		{
			throw new ValidationEngineException(e.getMessage());
		}
		return valid;
	}
	
	public void getChromosomeEntryNames(List<ChromosomeEntry> chromosomeEntries)
	{
		for(ChromosomeEntry chromosomeEntry: chromosomeEntries)
		{
			chromosomeNames.add(chromosomeEntry.getObjectName());
		}
	}
	
}
