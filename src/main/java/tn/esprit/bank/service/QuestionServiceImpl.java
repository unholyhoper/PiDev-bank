package tn.esprit.bank.service;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import tn.esprit.bank.entity.AccountRequest;
import tn.esprit.bank.entity.Question;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.enumeration.AccountRequestStatus;
import tn.esprit.bank.enumeration.QuestionStatus;
import tn.esprit.bank.enumeration.TransactionStatus;
import tn.esprit.bank.repository.AccountRequestRepository;
import tn.esprit.bank.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements IQuestionService  {
    @Autowired
   QuestionRepository questionRepository;




	@Override
	public Question findQuestionById(Long id) {
		// TODO Auto-generated method stub
		 if (questionRepository.findById(id).isPresent()) {
	            return questionRepository.findById(id).get();
	        } else {
	            return null;
	        }	}

	@Override
	public List<Question> getAllQuestion() {
		// TODO Auto-generated method stub
		return questionRepository.findAll();
	}

	@Override
	public Question createQuestion(Question question) {
		question.setStatus(QuestionStatus.CREATED);
	        return questionRepository.save(question);
	}

	@Override
	public void deleteQuestionById(Long id) {
		// TODO Auto-generated method stub
		questionRepository.deleteById(id);
	}

	@Override
	public Question updateQuestion(Long questionId, String status) {
		 Question question =questionRepository.findById(questionId).orElseThrow(() ->
         new RuntimeException("There is no Question found with ID = " + questionId)
 );
		 question.setStatus(QuestionStatus.valueOf(status));
	        return questionRepository.save(question);
	}
	
	
	
	
	
	 public void export(List<Question> listeQuestion ,HttpServletResponse response) throws DocumentException, IOException
	 {
		 Document document = new Document(PageSize.A4);
		 PdfWriter.getInstance(document, response.getOutputStream());
		 document.open();
       

	        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        fontTitle.setSize(18);
	        Paragraph paragraph = new Paragraph("Liste Of Question.", fontTitle);
	        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
	        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
	        fontParagraph.setSize(12);
	        Paragraph paragraph2 = new Paragraph("This is a paragraph.", fontParagraph);
	        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
	        document.add(paragraph);
	        
	        PdfPTable tb = new PdfPTable(3);
	        tb.setWidthPercentage(100f);
	        tb.setWidths(new float[] { 3.5f, 3.0f, 3.0f});
	        tb.setSpacingBefore(10);
	        writeQuestionHeader(tb);
	        writeQuestionData(tb,listeQuestion);
	        document.add(paragraph2);
	        document.add(tb);
	        document.close();
		 
		 
		 
	 }
	public void writeQuestionHeader(PdfPTable tb)
	{
      PdfPCell cell = new PdfPCell();
      cell.setBackgroundColor(Color.orange);
      cell.setPadding(5);
      Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
      font.setColor(Color.white);
      cell.setPhrase(new Phrase("id",font));
      tb.addCell(cell);
      cell.setPhrase(new Phrase("title",font));
      tb.addCell(cell);
      cell.setPhrase(new Phrase("description",font));
      tb.addCell(cell);
	}
	
	public void writeQuestionData(PdfPTable tb ,List<Question> liste)
	{
	for(Question question :liste)
		{
			tb.addCell(String.valueOf(question.getId()));
			tb.addCell(question.getTitle());
			tb.addCell(question.getDescription());

		}
      
		


	}
	
	
	 
	
}
