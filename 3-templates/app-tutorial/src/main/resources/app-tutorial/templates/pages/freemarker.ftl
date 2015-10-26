[#-- Special content properties --]
The content object is an instance of ContentMap and the following attributes are available:
The current node path: ${content.@path}
The current node name: ${content.@name}
The current node id: ${content.@id}
The current node depth: ${content.@depth}
The current node node type: ${content.@nodeType}
 
[#-- Context: ctx --]
The current user name ${ctx.user.name}
The current locale ${ctx.locale}
 
[#-- TemplatingFunctions: cmsfn--]
Create a link to a page: ${cmsfn.link(content)}
 
[#-- Status based rendering --]
This is ${cmsfn.authorInstance?string('indeed', 'not')} an author instance.
This is ${cmsfn.editMode?string('indeed', 'not')} the edit mode.
This is ${cmsfn.previewMode?string('indeed', 'not')} the preview mode.
 
[#if (content.@depth >= 2)]
   At least two level
[#elseif (content.@depth >= 1)]
   Root child
[#else]
   ROOT!
[/#if]

<br/>
[#macro print message level="INFO"]
    <br/> [${level}] ${.now?string("yyyy-MM-dd")} ${message}
    [#if message?starts_with('H')]
        'H' is a nice letter
    [/#if]
[/#macro]
[@print message = 'Hello'/]
[@print message = 'Magnolia' level = 'ERROR'/]

[@print messageQ = content.unknown/]