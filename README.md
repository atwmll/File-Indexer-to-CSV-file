# Indexer Documentation

![](https://img.shields.io/badge/Language-Java-yellow)
![](https://img.shields.io/badge/Build-Stable-success)
![](https://img.shields.io/badge/Status-Works%20as--is%2FWork--in--Progress-blueviolet)
![](https://img.shields.io/badge/Issues-Minor%2FExtra-yellowgreen)
![](https://img.shields.io/badge/Purpose-Enterprise/Small_biz-ff69b4)
![](https://img.shields.io/badge/Other%20Info%3A-Educational%2FProductivity-9cf)

## <u>Stop manually indexing files, you have a life!!</u>

### What is it?
* #### A simple tool that indexes large amounts of files and adds their names, along with additional information (defaulted, but easily configurable by you!) to a Comma Separated Values (.csv) file that can be simply opened using Libre/Open Office or alternatively Microsoft Excel.

### Usage:
* #### File organization
* #### Record keeping

### How to use:
#### (Test folders and files are provided!)
#### 1. Clone whole project
#### 2. Modify `Indexer.java`, `indexer.properties`, `log4j.properties` to your needs (paths, code, wording, etc..)
#### 3. Open terminal
#### 4. Change directory (cd) into 'Indexer-run-jar' folder
#### 5. Run: `java -jar -Dlog4j.configuration=file:"./log4j.properties" Indexer-1-jar-with-dependencies.jar`
#### 6. Profit!

# Technical Information:
#### Simple Overview:
#### 1. Scans directory of files (in our example/case: blank files with fake info in their names)
#### 2. Processes the filenames
#### 3. Maps description of the filename by reading values from a .xlsx/.csv file containing Description and ID
#### 4. Adds everything to a Comma Separated Values file (.csv - for Excel, Libre/Open Office)<br>In this format:<br>[Date and Time of Index, Account Numbers, Empty Cell, Mapping ID, Empty Cell, Example Hardcode Text, Example Hardcode Text, File Description (excluding commas if any), Full Filename (excluding commas if any), File Extension]
#### 5. The filename (notice it contains bad commas):<br>1234567890 - A PDF Document, 1.pdf<br>What the result is (double quotes are empty cells):<br>[10/21/2019 @ 11:05.29 AM,1234567890,,A00045,,atwmll,Just For Fun,A PDF Document 1,1234567890 - A PDF Document 1.pdf,PDF]

# Other Information

##### This code is free software -- utilize it for your organization as you please! I was assigned to make an indexer at my job and decided to share a less complicated and universal version of it because I believe it could be useful to many people outside of my organization.


##### The code is clearly commented, and contains useful examples on how you can use it for your specific needs.

##### If you have any suggestions, please feel free to pass along.
