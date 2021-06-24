package helmiarrazy.jwork_android;


/**
 * Kelas Recruiter, dalam kelas ini terdapat beberapa method acessor (get) dan mutator (set).
 * Serta adanya sebuah method yang bertipe void untuk melakukan printData.
 * Sesuai dengan nama kelasnya (Recruiter) yang berarti perekrut, sepertinya akan digunakan untuk mengatur segala data yang terkait dengan informasi perekrut atau rekrut pegawai.
 * Didalam kelas ini juga ada beberapa access modifier (public dan private) yang digunakan pada variable, method, atau constructor.
 * Access Modifier bersifat public berarti dapat diakses oleh kelas dari mana saja termasuk subclass.
 * Access Modifier bersifat private berarti hanya dapat diakses didalam kelas itu sendiri saja.
 *
 * @author Helmi Arrazy
 * @version 18-03-2021
 */
public class Recruiter {
    // Instance Variable
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;


    /**
     * Sebuah Constructor default yang bernama Recruiter.
     * Access Modifier pada constrcutor ini berjenis public.
     * Parameter digunakan sebagai nilai inputan suatu fungsi ketika fungsi tersebut didefinisikan.
     * Ada beberapa parameter yang digunakan untuk konstruktor ini, yang dapat langsung dilihat dibawah.
     *
     * @param id merupakan nilai inputan untuk variable id
     * @param name  merupakan nilai inputan untuk variable name
     * @param email  merupakan nilai inputan untuk variable email
     * @param phoneNumber merupakan nilai inputan untuk variable phoneNumber
     * @param location merupakan nilai inputan untuk variable location
     */
    public Recruiter(int id, String name, String email, String phoneNumber, Location location)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }


    /**
     * Sebuah method getter (accessor) yang bernama getId untuk mendapatkan/mengembalikkan data id.
     * Return type dari method ini adalah int.
     * Tidak ada parameter yang digunakan pada method ini.
     * Access modifier pada method berjenis public.
     *
     * @return id jadi mengembalikkan nilai yang ada didalam variable id
     */
    public int getId()
    {
        return id;
    }


    /**
     * Sebuah method getter (accessor) yang bernama getName untuk mendapatkan/mengembalikkan data name.
     * Return type dari method ini adalah String.
     * Tidak ada parameter yang digunakan pada method ini.
     * Access modifier pada method berjenis public.
     *
     * @return name jadi mengembalikkan nilai yang ada didalam variable name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Sebuah method getter (accessor) yang bernama getEmail untuk mendapatkan/mengembalikkan data email.
     * Return type dari method ini adalah String.
     * Tidak ada parameter yang digunakan pada method ini.
     * Access modifier pada method berjenis public.
     *
     * @return email jadi mengembalikkan nilai yang ada didalam variable email
     */
    public String getEmail()
    {
        return email;
    }


    /**
     * Sebuah method getter (accessor) yang bernama getPhoneNumber untuk mendapatkan/mengembalikkan data phonenumber.
     * Return type dari method ini adalah String.
     * Tidak ada parameter yang digunakan pada method ini.
     * Access modifier pada method berjenis public.
     *
     * @return phoneNumber jadi mengembalikkan nilai yang ada didalam variable phoneNumber
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }


    /**
     * Sebuah method getter (accessor) yang bernama getLocation untuk mendapatkan/mengembalikkan data location.
     * Return type dari method ini adalah Location (yang merupakan kelas).
     * Tidak ada parameter yang digunakan pada method ini.
     * Access modifier pada method berjenis public.
     *
     * @return location jadi mengembalikkan nilai yang ada didalam variable location
     */
    public Location getLocation()
    {
        return location;
    }


    /**
     * Sebuah method setter (mutator) yang bernama setId untuk menetapkan/mengisi nilai dari variable id.
     * Return type dari method ini adalah void.
     * Ada parameter yang digunakan pada method ini, yaitu id.
     * Access modifier pada method berjenis public.
     *
     * @param id digunakan sebagai inputan untuk variable id
     */
    public void setId(int id)
    {
        this.id = id;
    }


    /**
     * Sebuah method setter (mutator) yang bernama setEmail untuk menetapkan/mengisi nilai dari variable email.
     * Return type dari method ini adalah void.
     * Ada parameter yang digunakan pada method ini, yaitu email.
     * Access modifier pada method berjenis public.
     *
     * @param email digunakan sebagai inputan untuk variable email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }


    /**
     * Sebuah method setter (mutator) yang bernama setName untuk menetapkan/mengisi nilai dari variable name.
     * Return type dari method ini adalah void.
     * Ada parameter yang digunakan pada method ini, yaitu name.
     * Access modifier pada method berjenis public.
     *
     * @param name digunakan sebagai inputan untuk variable name
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * Sebuah method setter (mutator) yang bernama setPhoneNumber untuk menetapkan/mengisi nilai dari variable phoneNumber.
     * Return type dari method ini adalah void.
     * Ada parameter yang digunakan pada method ini, yaitu phoneNumber.
     * Access modifier pada method berjenis public.
     *
     * @param phoneNumber digunakan sebagai inputan untuk variable phoneNumber
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Sebuah method setter (mutator) yang bernama setLocation untuk menetapkan/mengisi nilai dari variable Location.
     * Return type dari method ini adalah void.
     * Ada parameter yang digunakan pada method ini, yaitu location.
     * Access modifier pada method berjenis public.
     *
     * @param location digunakan sebagai inputan untuk variable location
     */
    public void setLocation(Location location)
    {
        this.location = location;
    }

}
