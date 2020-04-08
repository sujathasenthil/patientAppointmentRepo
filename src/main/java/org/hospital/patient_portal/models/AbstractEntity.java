//package org.hospital.patient_portal.models;
//
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.MappedSuperclass;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//import java.util.Objects;
//
//@MappedSuperclass
//public class AbstractEntity {
////    @Id
////    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
////    @GenericGenerator(name = "native", strategy = "native")
//
//    @Id
//    @GeneratedValue
//    private int id;
//
//    public int getId() {
//        return id;
//    }
//
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        AbstractEntity entity = (AbstractEntity) o;
//        return id == entity.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
////    @Override
////    public String toString() {
////        return "AbstractEntity{" +
////                "id=" + id +
////                '}';
////    }
//}
//    //
//////   @NotBlank(message ="Name is required")
//////    @Size(min = 3, max=100, message="Name must be between 3 and 100 characters long")
//////    private String name;
////
////    public int getId() {
////        return id;
////    }
////
//////    public String getName() {
//////        return name;
//////    }
//////
//////    public void setName(String name) {
//////        this.name = name;
//////    }
////
//////    @Override
//////    public String toString() {
//////        return name;
//////    }
////
//////    @Override
//////    public boolean equals(Object o) {
//////        if (this == o) return true;
//////        if (o == null || getClass() != o.getClass()) return false;
//////        AbstractEntity that = (AbstractEntity) o;
//////        return id == that.id;
//////    }
//////
//////    @Override
//////    public int hashCode() {
//////        return Objects.hash(id);
//////    }
////}
