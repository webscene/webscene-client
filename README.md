# Web Scene Client (webscene-client)

Client side library for building and managing web UIs for Kotlin based projects.


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

To create a new DOM element (**org.w3c.dom.Element**) call the **toDomElement** function instead.