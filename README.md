Dumbtemplates
==============

Super simple "dumb" templating system for Java that exploits the wonderfully simple org.apache.commons.lang3.text.StrSubstitutor class.
Edit your dumbtemplate.properties file to point to your .html template files (template files should have an .html extension or no extension)
and use Dumbtemplates convenience methods to roll your own MVC framework. While Dumbtemplates is dead simple, it also supports
auto-reloading of your templates when new files are uploaded or existing files are updated.

Dumbtemplates is super lightweight and efficient and assumes you know what you're doing.

For more info on org.apache.commons.lang3.text.StrSubstitutor, see
http://commons.apache.org/proper/commons-lang/javadocs/api-3.1/org/apache/commons/lang3/text/StrSubstitutor.html

See the Javadoc for more info on using Dumbtemplates.

DEPENDENCIES: Since this is a maven project, all dependencies should be handled properly by the pom.xml file, except the Javatools dependency.
You'll probably have to checkout and build a jar of my Javatools to satisfy that dependency.
https://github.com/sbe1/javatools


Example 1 - process a template:

public static final DumbtemplateCollection templates = new DumbtemplateCollection.getInstance();

/* The data argument below is a Map&lt;String,Object&gt; of variables whose keys will be matched by StrSubstitutor to
${placeholders} in the template file home_page.html and return the entire processed template page
as a string.
*/

final String myView = Dumbtemplate.processTemplate(templates.getTemplate("home_page"), data);


Example 2 - template looping:

public static final DumbtemplateCollection templates = new DumbtemplateCollection.getInstance();


/* The loopValues argument below is a List&lt;Map&lt;String,Object&gt;&gt; of table row data that will be looped through by the doLoop() method
and return a String containing all processed rows. The "table_row_template" is an HTML snippet that contains the HTML for a single table row
and the StrSubstitutor ${placeholders} which will be used to build each row of the table. */

final String tableData = Template.doLoop(loopValues, templates.getTemplate("table_row_template"));


// create a Map of values for the main template.

final Map<String, Object> templateValues = new HashMap();

templateValues.put("title","My Page");

// add processed table rows as a template variable.

templateValues.put("tableData", tableData);

final String myView = Dumbtemplate.processTemplate(templates.getTemplate("my_page"), templateValues);
