import java.io.*;

class Entry
{
    public String name, number, notes;
}

public class PhoneBook
{
    public static Entry[] entryList = new Entry[100];
    static int num_entries=0;

    public static void main(String args[]) throws Exception
    {
        String cmd,nm,no,nt;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ReadPhoneBook("Book.txt");
        System.out.print("\n\t *** Total No. of Records imported in Array from File = "+num_entries+"***");

        System.out.print("\n\n Codes are entered as 1 to 8 characters.\n" + "Use \"e\" for enter, \"f\" for find, \"l\" to list, \"q\" to quit.");

        while(true)
        {
            System.out.print("\n Enter the Command = ");
            cmd = br.readLine() ;

            if(cmd.equalsIgnoreCase("e"))
            {
                entryList[num_entries] = new Entry();

                System.out.print("\n Enter Name = ");    nm = br.readLine() ;    entryList[num_entries].name = nm;
                System.out.print("\n Enter Number = ");  no = br.readLine() ;    entryList[num_entries].number = no;
                System.out.print("\n Enter Notes = ");   nt = br.readLine() ;    entryList[num_entries].notes = nt+"\n";

                num_entries ++;
                WritePhoneBook("Book.txt");
            }

            if(cmd.equalsIgnoreCase("f"))   StorePhoneBook();
            if(cmd.equalsIgnoreCase("l"))   listAllEntriess();
            if(cmd.equalsIgnoreCase("q"))    System.exit(0);
        }
     }

    public static void listAllEntriess()
    {
        for(int x=0;x<num_entries;x++)
            System.out.print(entryList[x].name + "\t" +entryList [x].number + "\t" +entryList [x].notes);
    }

    public static void StorePhoneBook ()throws Exception
    {
        String srch;
        int flg=0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("\n Enter String to Find = ");
        srch = br.readLine() ;

        for(int x=0;x<num_entries;x++)
        {
            if(srch.equalsIgnoreCase(entryList[x].name)||srch.equalsIgnoreCase(entryList[x].number)||srch.equalsIgnoreCase(entryList[x].notes ))
            {
                System.out.print(entryList[x].name + "\t" +entryList [x].number + "\t" +entryList [x].notes);
                flg=1;  break;
            }
        }

        if(flg==0)  System.out.println("\n\t *** Info NOT Found ***");
    }

    public static void ReadPhoneBook (String FileName) throws Exception
    {
        int i,tab=0;
        String s="";
        FileInputStream fin;

        fin = new FileInputStream(FileName);
        entryList[num_entries] = new Entry();

        do
        {
            i = fin.read();
            if((char)i != '*')     s=s+(char)i;

            if(i != -1)
            {
                if((char)i == '\t')
                {
                    tab ++;

                    if(tab==1)  entryList[num_entries].name=s;
                    if(tab==2)  entryList[num_entries].number=s;

                    s="";
                }
             
                if(tab==2 && (char)i == '*')
                 {
                     entryList[num_entries].notes=s;
                     s="";  tab=0;

                     num_entries ++;
                     entryList[num_entries] = new Entry();
                 }
            }
        } while(i != -1);

        for(int x=0;x<num_entries;x++)
            System.out.print(entryList[x].name + "\t" +entryList [x].number + "\t" +entryList [x].notes);

        fin.close();
    }

    public static void WritePhoneBook(String FileName) throws Exception
    {
        PrintStream P = new PrintStream(new FileOutputStream(FileName));

        for (int i=0; i < num_entries; i++)
            P.print(entryList[i].name + "\t" +entryList [i].number + "\t" +entryList [i].notes+"*");

        P.close();
        System.out.println("Phonebook Updated.");
    }
}
