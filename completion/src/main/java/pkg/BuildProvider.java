package pkg;

import picocli.CommandLine;

public class BuildProvider implements CommandLine.IVersionProvider {
    /**
     * requires:
     * <pre>
     * tasks.withType(Jar) { manifest { attributes('Implementation-Version': 'v1') } }
     * </pre>
     * or
     * <pre>
     * tasks.withType(Jar) { manifest { attributes('Specification-Version': 'v1') } }
     * </pre>
     */
    @Override
    public String[] getVersion() {
        Package p = getClass().getPackage();
        String implementationVersion = p.getImplementationVersion();
        if (implementationVersion != null)
            return new String[]{implementationVersion};
        String specificationVersion = p.getSpecificationVersion();
        if (specificationVersion != null)
            return new String[]{specificationVersion};
        return new String[0];
    }
}
