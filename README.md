Dumbtemplates
==============

Super simple "dumb" templating system for Java that exploits the wonderfully simple org.apache.commons.lang3.text.StrSubstitutor class.
Edit your dumbtemplate.properties file to point to your .html template files (template files must have an .html extension)
and use Dumbtemplates convenience methods to roll your own MVC framework. While Dumbtemplates is dead simple, it also supports
auto-reloading of your templates when new files are uploaded or existing files are updated.

Dumbtemplates is super lightweight and efficient and assumes you know what you're doing.

For more info on org.apache.commons.lang3.text.StrSubstitutor, see
http://commons.apache.org/proper/commons-lang/javadocs/api-3.1/org/apache/commons/lang3/text/StrSubstitutor.html

See the Javadoc for more info on using Dumbtemplates.

DEPENDENCIES: All dependencies should be handled properly by he pom.xml file, except the Javatools dependency.
You'll probably have to checkout and build a jar of my Javatools to satisfy that dependency.
https://github.com/sbe1/javatools


Example:

public static final DumbtemplateCollection templates = new DumbtemplateCollection();

/* The data argument below is a Map&lt;String,Object&gt; of variables whose keys will be matched by StrSubstitutor to
${placeholders} in the template file home_page.html and return the entire processed template page
as a string.
*/

final String myView = Dumbtemplate.processTemplate(templates.getTemplate("home_page"), data);

