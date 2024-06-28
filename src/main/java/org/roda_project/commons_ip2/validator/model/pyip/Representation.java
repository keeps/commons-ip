package org.roda_project.commons_ip2.validator.model.pyip;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.roda_project.commons_ip2.validator.constants.Constants;

/** Representation. */
public class Representation {
  /**
   * {@link String}.
   */
  @JsonProperty("name")
  private String name = null;

  /**
   * Set the name.
   * 
   * @param name
   *          {@link String}.
   * @return {@link Representation}.
   */
  public Representation name(final String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name.
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public boolean equals(final java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Representation representation = (Representation) o;
    return Objects.equals(this.name, representation.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {

      String sb = "class Representation {\n" +
              "    name: " + toIndentedString(name) + Constants.END_OF_LINE +
              "}";
    return sb;
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(final java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace(Constants.END_OF_LINE, "\n    ");
  }
}
