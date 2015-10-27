[#-- Special content properties --]
The content object is an instance of ContentMap and the following attributes are available:<br/>
The current node path: ${content.@path}<br/>
The current node name: ${content.@name}<br/>
The current node id: ${content.@id}<br/>
The current node depth: ${content.@depth}<br/>
The current node node type: ${content.@nodeType}<br/>

JCR node: ${cmsfn.asJCRNode(content)}<br/>
[#list (cmsfn.children(content)) as child]
    JCR child node: ${cmsfn.asJCRNode(child)}<br/>
[/#list]

[#-- Context: ctx --]
The current user name ${ctx.user.name} <br/>
The current locale ${ctx.locale} <br/>
 
[#-- TemplatingFunctions: cmsfn--]
Create a link to a page: ${cmsfn.link(content)}<br/>
 
[#-- Status based rendering --]
This is ${cmsfn.authorInstance?string('indeed', 'not')} an author instance.<br/>
This is ${cmsfn.editMode?string('indeed', 'not')} the edit mode.<br/>
This is ${cmsfn.previewMode?string('indeed', 'not')} the preview mode.<br/>
 
[#if (content.@depth >= 3)]
   At least two level<br/>
[#elseif (content.@depth == 2)]
   Root child<br/>
[#else]
   ROOT!<br/>
[/#if]

<br/>
[#macro print message level="INFO"]
    [${level}] ${.now?string("yyyy-MM-dd")} ${message}<br/>
    [#if message?starts_with('H')]
        'H' is a nice letter<br/>
    [/#if]
[/#macro]
[@print message = 'Hello'/]
[@print message = 'Magnolia' level = 'ERROR'/]

[@print messageQ = content.unknown/]