package net.oharagroup.confluence.plugins;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.content.render.xhtml.DefaultConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import com.atlassian.renderer.RenderContext;
import com.atlassian.renderer.v2.macro.BaseMacro;
import com.atlassian.renderer.v2.macro.MacroException;
import com.atlassian.renderer.v2.RenderMode;
import java.util.Map;
import org.apache.velocity.VelocityContext;

public class SequenceDiagramMacro extends BaseMacro implements Macro
{
	public SequenceDiagramMacro()
	{
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String execute(final Map params, final String body, final RenderContext renderContext) throws MacroException
	{
		VelocityContext context = new VelocityContext(MacroUtils.defaultVelocityContext());
		context.put("theme", (String) params.get("theme"));
		context.put("diagram", body);
		return VelocityUtils.getRenderedTemplate("net/oharagroup/confluence/plugins/sequence-diagram.vm", context);
	}

	@Override
	public String execute(final Map<String, String> params, final String body, final ConversionContext conversionContext) throws MacroExecutionException
	{
		try {
			DefaultConversionContext defaultConversionContext = (DefaultConversionContext) conversionContext;
			RenderContext renderContext = defaultConversionContext.getPageContext();
			return execute(params, body, renderContext);
		} catch (MacroException e) {
			throw new MacroExecutionException(e);
		}
	}

	@Override
	public BodyType getBodyType()
	{
		return BodyType.PLAIN_TEXT;
	}

	@Override
	public OutputType getOutputType()
	{
		return OutputType.BLOCK;
	}

	@Override
	public boolean isInline() {
		return false;
	}

	@Override
	public boolean hasBody() {
		return true;
	}

	@Override
	public RenderMode getBodyRenderMode() {
		return RenderMode.ALL;
	}

	@Override
	public boolean suppressMacroRenderingDuringWysiwyg() {
		return true;
	}

	@Override
	public boolean suppressSurroundingTagDuringWysiwygRendering() {
		return false;
	}
}
