package com.deutschbible.hindibible;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 5/28/2018.
 */
public class BooksChapters {

    public static int  getChaptersCount(int book)
    {
        HashMap<Integer,Integer> booksChapters = new HashMap<Integer,Integer>();
        booksChapters.put(	1 , 50	);
        booksChapters.put(	2 , 40	);
        booksChapters.put(	3 , 27	);
        booksChapters.put(	4 , 36	);
        booksChapters.put(	5 , 34	);
        booksChapters.put(	6 , 24	);
        booksChapters.put(	7 , 21	);
        booksChapters.put(	8 , 4	);
        booksChapters.put(	9 , 31	);
        booksChapters.put(	10 , 24	);
        booksChapters.put(	11 , 22	);
        booksChapters.put(	12 , 25	);
        booksChapters.put(	13 , 29	);
        booksChapters.put(	14 , 36	);
        booksChapters.put(	15 , 10	);
        booksChapters.put(	16 , 13	);
        booksChapters.put(	17 , 10	);
        booksChapters.put(	18 , 42	);
        booksChapters.put(	19 , 150);
        booksChapters.put(	20 , 31	);
        booksChapters.put(	21 , 12	);
        booksChapters.put(	22 , 8	);
        booksChapters.put(	23 , 66	);
        booksChapters.put(	24 , 52	);
        booksChapters.put(	25 , 5	);
        booksChapters.put(	26 , 48	);
        booksChapters.put(	27 , 12	);
        booksChapters.put(	28 , 14	);
        booksChapters.put(	29 , 3	);
        booksChapters.put(	30 , 9	);
        booksChapters.put(	31 , 1	);
        booksChapters.put(	32 , 4	);
        booksChapters.put(	33 , 7	);
        booksChapters.put(	34 , 3	);
        booksChapters.put(	35 , 3	);
        booksChapters.put(	36 , 3	);
        booksChapters.put(	37 , 2	);
        booksChapters.put(	38 , 14	);
        booksChapters.put(	39 , 4	);
        booksChapters.put(	40 , 28	);
        booksChapters.put(	41 , 16	);
        booksChapters.put(	42 , 24	);
        booksChapters.put(	43 , 21	);
        booksChapters.put(	44 , 28	);
        booksChapters.put(	45 , 16	);
        booksChapters.put(	46 , 16	);
        booksChapters.put(	47 , 13	);
        booksChapters.put(	48 , 6	);
        booksChapters.put(	49 , 6	);
        booksChapters.put(	50 , 4	);
        booksChapters.put(	51 , 4	);
        booksChapters.put(	52 , 5	);
        booksChapters.put(	53 , 3	);
        booksChapters.put(	54 , 6	);
        booksChapters.put(	55 , 4	);
        booksChapters.put(	56 , 3	);
        booksChapters.put(	57 , 1	);
        booksChapters.put(	58 , 13	);
        booksChapters.put(	59 , 5	);
        booksChapters.put(	60 , 5	);
        booksChapters.put(	61 , 3	);
        booksChapters.put(	62 , 5	);
        booksChapters.put(	63 , 1	);
        booksChapters.put(	64 , 1	);
        booksChapters.put(	65 , 1	);
        booksChapters.put(	66 , 22	);
        return booksChapters.get(book);
    }

    public static String getBookName(int booknumber)
    {
        Map<Integer,String> bookName = new HashMap<Integer,String>();
        bookName.put(1 ,"उत्पत्ति-Genesis" );
        bookName.put(2 ,"निर्गमन-Exodus" );
        bookName.put(3 ,"लैव्यव्यवस्था-Leviticus");
        bookName.put(4 ,"गिनती--Numbers" );
        bookName.put(5 ,"व्यवस्थाविवरण-Deuteronomy" );
        bookName.put(6 ,"यहोशू-Joshua" );
        bookName.put(7 ,"न्यायियों-Judges" );
        bookName.put(8 ,"रूत-Ruth" );
        bookName.put(9 ,"1 शमूएल-1 Samuel" );
        bookName.put(10 ,"2 शमूएल-2 Samuel" );
        bookName.put(11 ,"1 राजा-1 Kings" );
        bookName.put(12 ,"2 राजा-2 Kings" );
        bookName.put(13 ,"1 इतिहास-1 Chronicles" );
        bookName.put(14 ,"2 इतिहास-2 Chronicles" );
        bookName.put(15 ,"एज्रा-Ezr" );
        bookName.put(16 ,"नहेमायाह-Nehemiah" );
        bookName.put(17 ,"एस्तेर-Esther" );
        bookName.put(18 ,"अय्यूब-Job" );
        bookName.put(19 ,"भजन संहिता-Psalms" );
        bookName.put(20 ,"नीतिवचन-Proverbs" );
        bookName.put(21 ,"सभोपदेशक-Ecclesiastes" );
        bookName.put(22 ,"श्रेष्ठगीत-Song of Songs" );
        bookName.put(23 ,"यशायाह-Isaiah" );
        bookName.put(24 ,"यिर्मयाह-Jeremiah" );
        bookName.put(25 ,"विलापगीत-Lamentations" );
        bookName.put(26 ,"यहेजकेल-Ezekiel" );
        bookName.put(27 ,"दानिय्येल-Daniel" );
        bookName.put(28 ,"होशे-Hosea" );
        bookName.put(29 ,"योएल-Joel" );
        bookName.put(30 ,"आमोस-Amos" );
        bookName.put(31 ,"ओबद्दाह-Obadiah" );
        bookName.put(32 ,"योना-Jonah" );
        bookName.put(33 ,"मीका-Micah" );
        bookName.put(34 ,"नहूम-Nahum" );
        bookName.put(35 ,"हबक्कूक-Habakkuk" );
        bookName.put(36 ,"सपन्याह-Zephaniah" );
        bookName.put(37 ,"हाग्गै-Haggai" );
        bookName.put(38 ,"जकर्याह-Zechariah" );
        bookName.put(39 ,"मलाकी-Malachi" );
        bookName.put(40 ,"मत्ती-Matthew" );
        bookName.put(41 ,"मरकुस-Mark" );
        bookName.put(42 ,"लूका-Luke" );
        bookName.put(43 ,"यूहन्ना-John" );
        bookName.put(44 ,"प्रेरितों के काम-Acts" );
        bookName.put(45 ,"रोमियो-Romans" );
        bookName.put(46 ,"1 कुरिन्थियों- 1 Corinthians" );
        bookName.put(47 ,"2 कुरिन्थियों - 2 Corinthians" );
        bookName.put(48 ,"गलातियों - Galatians" );
        bookName.put(49 ,"इफिसियों - Ephesians" );
        bookName.put(50 ,"फिलिप्पियों-Philippians" );
        bookName.put(51 ,"कुलुस्सियों-Colossians" );
        bookName.put(52 ,"1 थिस्सलुनीकियों-1 Thessalonians" );
        bookName.put(53 ,"2 थिस्सलुनीकियो - 2 Thessalonians" );
        bookName.put(54 ,"1 तीमुथियुस - 1 Timothy" );
        bookName.put(55 ,"2 तीमुथियुस - 2 Timothy" );
        bookName.put(56 ,"तीतुस - Titus" );
        bookName.put(57 ,"फिलेमोन - Philemon" );
        bookName.put(58 ,"इब्रानियों - Hebrews" );
        bookName.put(59 ,"याकूब - James" );
        bookName.put(60 ,"1 पतरस - 1 Peter" );
        bookName.put(61 ,"2 पतरस - 2 Peter" );
        bookName.put(62 ,"1 यूहन्ना- 1 John" );
        bookName.put(63 ,"2 यूहन्ना- 2 John" );
        bookName.put(64 ,"3 यूहन्ना-3  John" );;
        bookName.put(65 ,"यहूदा-Jude" );
        bookName.put(66 ,"प्रकाशित वाक्य-Revelation" );
        return bookName.get(booknumber);

    }

}
