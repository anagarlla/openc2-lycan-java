package org.oasis.openc2.lycan.profiles.th.args;

import java.util.Map;
import org.oasis.openc2.lycan.targets.Artifact;
import org.oasis.openc2.lycan.targets.Device;
import org.oasis.openc2.lycan.targets.File;
import org.oasis.openc2.lycan.targets.Ipv4Connection;
import org.oasis.openc2.lycan.targets.Ipv6Connection;
import org.oasis.openc2.lycan.targets.Process;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Datos OpenC2 incluidos en native_oc2, con los nombres definidos por el JADN TH.
 * Este Record permite varios campos; no es un Target ni un Choice.
 * Las cadenas se almacenan en su representacion JSON; falta validar los tipos ls.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LanguageSpecTypes {
    @JsonProperty("artifact")
    private Artifact artifact;

    @JsonProperty("device")
    private Device device;

    @JsonProperty("domain_name")
    private String domainName;

    @JsonProperty("email-address")
    private String emailAddress;

    @JsonProperty("file")
    private File file;

    @JsonProperty("hashes")
    private Map<String, String> hashes;

    @JsonProperty("hostname")
    private String hostname;

    @JsonProperty("idn_domain_name")
    private String idnDomainName;

    @JsonProperty("idn_email_address")
    private String idnEmailAddress;

    @JsonProperty("ipv4_address")
    private String ipv4Address;

    @JsonProperty("ipv6_address")
    private String ipv6Address;

    @JsonProperty("ipv4_network")
    private String ipv4Network;

    @JsonProperty("ipv6_network")
    private String ipv6Network;

    @JsonProperty("ipv4_connection")
    private Ipv4Connection ipv4Connection;

    @JsonProperty("ipv6_connection")
    private Ipv6Connection ipv6Connection;

    @JsonProperty("iri")
    private String iri;

    @JsonProperty("mac_address")
    private String macAddress;

    @JsonProperty("port")
    private Integer port;

    @JsonProperty("process")
    private Process process;

    @JsonProperty("uri")
    private String uri;

    public LanguageSpecTypes() {
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Map<String, String> getHashes() {
        return hashes;
    }

    public void setHashes(Map<String, String> hashes) {
        this.hashes = hashes;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIdnDomainName() {
        return idnDomainName;
    }

    public void setIdnDomainName(String idnDomainName) {
        this.idnDomainName = idnDomainName;
    }

    public String getIdnEmailAddress() {
        return idnEmailAddress;
    }

    public void setIdnEmailAddress(String idnEmailAddress) {
        this.idnEmailAddress = idnEmailAddress;
    }

    public String getIpv4Address() {
        return ipv4Address;
    }

    public void setIpv4Address(String ipv4Address) {
        this.ipv4Address = ipv4Address;
    }

    public String getIpv6Address() {
        return ipv6Address;
    }

    public void setIpv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
    }

    public String getIpv4Network() {
        return ipv4Network;
    }

    public void setIpv4Network(String ipv4Network) {
        this.ipv4Network = ipv4Network;
    }

    public String getIpv6Network() {
        return ipv6Network;
    }

    public void setIpv6Network(String ipv6Network) {
        this.ipv6Network = ipv6Network;
    }

    public Ipv4Connection getIpv4Connection() {
        return ipv4Connection;
    }

    public void setIpv4Connection(Ipv4Connection ipv4Connection) {
        this.ipv4Connection = ipv4Connection;
    }

    public Ipv6Connection getIpv6Connection() {
        return ipv6Connection;
    }

    public void setIpv6Connection(Ipv6Connection ipv6Connection) {
        this.ipv6Connection = ipv6Connection;
    }

    public String getIri() {
        return iri;
    }

    public void setIri(String iri) {
        this.iri = iri;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}