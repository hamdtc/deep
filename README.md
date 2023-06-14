# deep
SnoopDev
Snoop Deep Web to crawl a website and get its html and screenshot using stormcrawler running in local mode in Apache Storm.

Here are the brief steps which I followed for executing the task in Java:
Step 1: Setting up the Environment

Installed and configured the Apache Storm and Selenium in my system Created a new Java project and added new dependencies for Storm and Selenium

Step 2: Crawling the Websites

Created a new Spout for reading seed URLs from a JSON file Created a new Bolt for fetching HTML and image data using Selenium Defined a Topology that connects the Spout and Bolt and submit it to Storm

Step 3: Stored the Data in the Database

Used a database library such as JDBC to connect to my chosen backend MySQL Created a new Bolt for storing the extracted data in the database Added the Bolt to my Topology to store the data as it is crawled

Step 4: Creating the Result JSON

Once I stored the HTML data and images in the database, I created the result JSON file.
