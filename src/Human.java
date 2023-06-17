public class Human {
    private String surname;
    private String name;
    private String lastname;
    private String birth;
    private String phone;
    private Character gender;

    public Human(String surname, String name, String lastname, String birth, String phone, Character gender) {
        this.surname = surname;
        this.name = name;
        this.lastname = lastname;
        this.birth = birth;
        this.phone = phone;
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "<" + surname + '>' + "<" + name + '>' + "<" + lastname + '>' +
                "<" + birth + '>' + "<" + phone + '>' + "<" + gender + '>';
    }
}
