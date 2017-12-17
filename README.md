# Web Scene Client (webscene-client)

Client side library for building and managing web UIs for Kotlin based projects.


## Installation

To install the library do the following:

1. Clone the **webscene/webscene-client** Git repository from GitHub
2. Import the cloned repository into IntelliJ
3. Create all JAR files (source, documentation, library) by running the **createAllJarFiles** Gradle task
4. Copy the contents of **build/libs** directory to this directory in your project: **libs/org/webscene/webscene-client/{version}**
5. Assuming Gradle is used in your project edit your **build.gradle.kts** file, and insert the following:

```kotlin
// ...

repositories {
    mavenCentral()
    maven {
        url = uri("libs")
    }
}
```

6. Add this line in your **build.gradle** file to add the library as a dependency:

```kotlin
compile("org.webscene:webscene-client:version")
```


## Basic Usage

Use the **org.webscene.client.html.HtmlCreator** object to create HTML elements. Below is an example:

```kotlin
import org.webscene.client.html.HtmlCreator as html

fun main(args: Array<String>) {
    html.div {
        parentElement("p") {
            element("b") { +"Hello World! :)" }
        }
    }
}
```


Once the HTML element is created you call the **createText** function off the object to generate the HTML in text form. Here is what the HTML will look like after calling the **createText** function (based on the example above):

```html
<div>
    <p>
        <b>Hello World! :)</b>
    </p>
</div>
```

To create a new DOM element (**org.w3c.dom.Element**) call the **toDomElement** function instead. Use the **DomEditor** (org.webscene.client.dom.DomEditor) object for editing the DOM. Below is an example:

```kotlin
// ...

fun setupHomePage() {
    // ...

    DomEditor.editSection(HtmlSection.BODY) (mainLayout, DomEditType.PREPEND)
}
```


## DOM Queries

Can do a DOM query through the **DomQuery** (org.webscene.client.dom.DomQuery) object for an element by ID. Below is an example:

```kotlin
val mainLayout = DomQuery.elementById("mainLayout")
```

Queries can also be done to find elements by class name(s). Below is an example:

```kotlin
val headerItems = DomQuery.allElementsByClassNames("heading-item")
```

Tags can even be queried. Below is an example:

```kotlin
val buttons = DomQuery.allElementsByTagName("button")
```