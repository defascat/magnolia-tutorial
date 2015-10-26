[#assign node=cmsfn.contentByPath(content.data, 'products')]
<div class="car">
    <h4>${content.data}</h4>
    <h4>${node.title!""}</h4>
    <span>${node.description!""}</span>
    [#if node.image?has_content]
        <img src="${cmsfn.link('dam', node.image?replace("jcr:", ""))}"/>
    [/#if]
</div>