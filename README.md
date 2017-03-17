# Web Scene Client (webscene-client)

Client side library for building and managing web UIs for Kotlin based projects.


## Installation

To install the library do the following:

1. Clone the **webscene/webscene-client** Git repository from GitHub
2. Import the cloned repository using IntelliJ
3. Generate the documentation by running the **createDokkaJar** Gradle task
4. Create the JAR file by running the **jar** Gradle task
5. Copy the contents of **build/libs** directory to this directory in your project: **libs/org/webscene/webscene-client/version**
6. Assuming Gradle is used in your project edit your **build.gradle** file, and insert the following:

```groovy
repositories {
    mavenCentral()
    maven {
        url uri("libs")
    }
}
```

7. Add this line in your **build.gradle** file to add the library as a dependency:

```groovy
compile "org.webscene:webscene-client:version"
```


## Usage

Use the **org.webscene.client.WebScene** object to create HTML elements. Below is an example:

```kotlin
import org.webscene.client.WebScene as ws

@JvmStatic
fun main(args: Array<String>) {
    ws.parentHtmlElement("div") {
        parentHtmlElement("p") {
            htmlElement("b") {
                +"Hello World! :)"
            }
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

To create a new DOM element (**org.w3c.dom.Element**) call the **toDomElement** function instead. Use the DomEditor (**WebScene.DomEditor**) object for editing the DOM. Below is an example:

```kotlin
// ...

fun editDom() {
    // ...

    ws.DomEditor.prependElementToBody(mainLayout.toDomElement())
}
```
