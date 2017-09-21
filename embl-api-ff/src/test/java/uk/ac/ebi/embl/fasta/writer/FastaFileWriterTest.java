package uk.ac.ebi.embl.fasta.writer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import org.junit.Test;
import uk.ac.ebi.embl.api.entry.Entry;
import uk.ac.ebi.embl.api.entry.EntryFactory;
import uk.ac.ebi.embl.api.entry.Text;
import uk.ac.ebi.embl.api.entry.sequence.Sequence;
import uk.ac.ebi.embl.api.entry.sequence.SequenceFactory;

public class FastaFileWriterTest
{
	@Test
   public void testwrite_entry() throws IOException
   {
	   String output= ">EM_XXX:ad0897987 ad0897987.1 STD:adfddfgghhjkll\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n";
	   StringWriter writer = new StringWriter();
	   Entry entry= new EntryFactory().createEntry();
	   entry.setPrimaryAccession("ad0897987");
	   entry.setDivision("XXX");
	   entry.setDescription(new Text("adfddfgghhjkll"));
	   entry.setDataClass(Entry.STD_DATACLASS);
	   Sequence sequence = new SequenceFactory().createSequence();
	   sequence.setSequence(ByteBuffer.wrap("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".getBytes()));
	   sequence.setAccession("ad0897987");
	   sequence.setVersion(1);
	   entry.setSequence(sequence);
	   FastaFileWriter fileWriter= new FastaFileWriter(entry,writer);
	   fileWriter.write();
	   assertEquals(output,writer.toString());
   }
}
