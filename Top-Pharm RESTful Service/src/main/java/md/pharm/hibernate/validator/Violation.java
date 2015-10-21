package md.pharm.hibernate.validator;

/**
 * Created by Andrei on 10/17/2015.
 */
public class Violation {
    String propertyPath;
    String interpolatedMessage;
    String rootBeanClass;

    public Violation(){}

    public Violation(String propertyPath, String interpolatedMessage, String rootBeanClass) {
        this.propertyPath = propertyPath;
        this.interpolatedMessage = interpolatedMessage;
        this.rootBeanClass = rootBeanClass;
    }

    public String getPropertyPath() {
        return propertyPath;
    }

    public void setPropertyPath(String propertyPath) {
        this.propertyPath = propertyPath;
    }

    public String getInterpolatedMessage() {
        return interpolatedMessage;
    }

    public void setInterpolatedMessage(String interpolatedMessage) {
        this.interpolatedMessage = interpolatedMessage;
    }

    public String getRootBeanClass() {
        return rootBeanClass;
    }

    public void setRootBeanClass(String rootBeanClass) {
        this.rootBeanClass = rootBeanClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Violation that = (Violation) o;

        if (propertyPath != null ? !propertyPath.equals(that.propertyPath) : that.propertyPath != null)
            return false;
        if (interpolatedMessage != null ? !interpolatedMessage.equals(that.interpolatedMessage) : that.interpolatedMessage != null)
            return false;
        return !(rootBeanClass != null ? !rootBeanClass.equals(that.rootBeanClass) : that.rootBeanClass != null);

    }

    @Override
    public int hashCode() {
        int result = propertyPath != null ? propertyPath.hashCode() : 0;
        result = 31 * result + (interpolatedMessage != null ? interpolatedMessage.hashCode() : 0);
        result = 31 * result + (rootBeanClass != null ? rootBeanClass.hashCode() : 0);
        return result;
    }
}
