package kev.demo;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.RAMDirectory;

import java.io.*;

public class Demo05_HighLighter {

    public static Analyzer analyzer = new SmartChineseAnalyzer();
    public static IndexWriterConfig config = new IndexWriterConfig(
            analyzer);
    public static RAMDirectory ramDirectory = new RAMDirectory();
    public static IndexWriter indexWriter;

    public static String readFileString(String file) {
        StringBuffer text = new StringBuffer();
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(new File(file)), "UTF8"));
            String line;
            while ((line = in.readLine()) != null) {
                text.append(line + "\r\n");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Document doc = new Document(); // create a new document

        /**
         * Create a field with term vector enabled
         */
        FieldType type = new FieldType();
        type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        type.setStored(true);
        type.setStoreTermVectors(true);
        type.setTokenized(true);
        type.setStoreTermVectorOffsets(true);

        Field field = new Field("title",
                "How to read UTF8 text file into String in Java", type); //term vector enabled
        Field f = new TextField("content", readFileString("E:\\a.txt"),
                Field.Store.YES);
        doc.add(field);
        doc.add(f);

        try {
            indexWriter = new IndexWriter(ramDirectory, config);
            indexWriter.addDocument(doc);
            indexWriter.close();

            IndexReader idxReader = DirectoryReader.open(ramDirectory);
            IndexSearcher idxSearcher = new IndexSearcher(idxReader);
            Query queryToSearch = new QueryParser("title", analyzer).parse("read file string utf8 精准扶贫办理特征政治");
            TopDocs hits = idxSearcher
                    .search(queryToSearch, idxReader.maxDoc());
            SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
            Highlighter highlighter = new Highlighter(htmlFormatter,
                    new QueryScorer(queryToSearch));

            System.out.println("reader maxDoc is " + idxReader.maxDoc());
            System.out.println("scoreDoc size: " + hits.scoreDocs.length);
            for (int i = 0; i < hits.totalHits; i++) {
                int id = hits.scoreDocs[i].doc;
                Document docHit = idxSearcher.doc(id);
                String text = docHit.get("content");
//                System.out.println(text);//这里没问题，全部显示
                //加入了以下一行代码后才能完全显示，否则达到一定长度后余下的字符无法处理
                highlighter.setTextFragmenter(new SimpleFragmenter(text.length()));
                TokenStream tokenStream = TokenSources.getAnyTokenStream(idxReader, id, "content", analyzer);
                System.out.println(tokenStream.toString());
                TextFragment[] frag = highlighter.getBestTextFragments(tokenStream, text, false, 10);
                for (TextFragment textFragment : frag) {
                    System.out.println(textFragment.toString());//这里有问题，一定长度后的无法显示
                }
                for (int j = 0; j < frag.length; j++) {
                    if ((frag[j] != null) && (frag[j].getScore() > 0)) {
                        System.out.println((frag[j].toString()));//这里有问题，一定长度后的无法显示
                    }
                }

                System.out.println("start highlight the title");
                // Term vector
                text = docHit.get("title");
                tokenStream = TokenSources.getAnyTokenStream(
                        idxSearcher.getIndexReader(), hits.scoreDocs[i].doc,
                        "title", analyzer);
                frag = highlighter.getBestTextFragments(tokenStream, text,
                        false, 10);
                for (int j = 0; j < frag.length; j++) {
                    if ((frag[j] != null) && (frag[j].getScore() > 0)) {
                        System.out.println((frag[j].toString()));
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}