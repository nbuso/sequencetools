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
package uk.ac.ebi.embl.api.validation.file;

import static org.junit.Assert.assertTrue;
import java.sql.SQLException;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import uk.ac.ebi.embl.api.validation.ValidationEngineException;
import uk.ac.ebi.embl.api.validation.annotation.Description;
import uk.ac.ebi.embl.api.validation.check.file.FastaFileValidationCheck;
import uk.ac.ebi.embl.api.validation.helper.FlatFileComparatorException;
import uk.ac.ebi.embl.api.validation.submission.Context;
import uk.ac.ebi.embl.api.validation.submission.SubmissionFile;
import uk.ac.ebi.embl.api.validation.submission.SubmissionFiles;
import uk.ac.ebi.embl.api.validation.submission.SubmissionOptions;

@Description("")
public class FastaFileValidationCheckTest extends SubmissionValidationTest
{
   @Before
   public void init() throws SQLException
   {   
	   options = new SubmissionOptions();
       options.source= Optional.of(getSource());
       options.assemblyInfoEntry= Optional.of(getAssemblyinfoEntry());
       options.isRemote = true;
   }
	
	@Test
	public void testInvalidFastaFile() throws ValidationEngineException
	{
		validateMaster(Context.genome);
		SubmissionFile file=initSubmissionTestFile("invalid_fasta_sequence.txt",SubmissionFile.FileType.FASTA);
		SubmissionFiles submissionFiles = new SubmissionFiles();
		submissionFiles.addFile(file);
        options.submissionFiles =Optional.of(submissionFiles);
        options.reportDir = Optional.of(file.getFile().getParent());
        options.context = Optional.of(Context.genome);
        options.init();
		FastaFileValidationCheck check = new FastaFileValidationCheck(options);
		assertTrue(!check.check(file));
		assertTrue(check.getMessageStats().get("SQ.1")!=null);
	}
	
	@Test
	public void testTranscriptomFixedvalidFastaFile() throws ValidationEngineException, FlatFileComparatorException
	{
		validateMaster(Context.transcriptome);
		SubmissionFile file=initSubmissionFixedTestFile("valid_transcriptom_fasta.txt",SubmissionFile.FileType.FASTA);
		SubmissionFiles submissionFiles = new SubmissionFiles();
		submissionFiles.addFile(file);
        options.submissionFiles =Optional.of(submissionFiles);
        options.reportDir = Optional.of(file.getFile().getParent());
        options.context = Optional.of(Context.transcriptome);
        options.init();
		FastaFileValidationCheck check = new FastaFileValidationCheck(options);
		assertTrue(check.check(file));
        assertTrue(compareOutputFiles(file.getFile()));
	}
	
	@Test
	public void testgenomeFixedvalidFastaFile() throws ValidationEngineException, FlatFileComparatorException
	{
		validateMaster(Context.genome);
		SubmissionFile file=initSubmissionFixedTestFile("valid_genome_fasta.txt",SubmissionFile.FileType.FASTA);
		SubmissionFiles submissionFiles = new SubmissionFiles();
		submissionFiles.addFile(file);
        options.submissionFiles =Optional.of(submissionFiles);
        options.reportDir = Optional.of(file.getFile().getParent());
        options.context = Optional.of(Context.genome);
        options.init();
		FastaFileValidationCheck check = new FastaFileValidationCheck(options);
		assertTrue(check.check(file));
        assertTrue(compareOutputFiles(file.getFile()));
	}
		
}
