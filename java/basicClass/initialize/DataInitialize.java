package lab1.initialize;

import lab1.entity.Mage;
import lab1.services.MageService;

public class DataInitialize {
    private final MageService mageService;

    public DataInitialize(MageService mageService) { this.mageService = mageService; }

    public void init(){
        Mage dziubich = new Mage("Dziubich", 3, 6.0);
        Mage strojek = new Mage("Strojek", 2, 5.0);
        Mage nowak = new Mage("Nowak", 2, 4.99);
        Mage wisnia =        new Mage("Wisnia", 2, 4.50);
        Mage kulpas =        new Mage("Kulpas", 2, 5.1);
        Mage wrzosek =        new Mage("Wrzosek", 1, 4.0);
        Mage zmudson =        new Mage("Zmudson", 1, 1.0);
        Mage weektor =       new Mage("Weektor", 1, 0.997);
        Mage kobas =       new Mage("Kobas", 1, 0.999);
        Mage pawelo =      new Mage("Pawelo", 1, 2.0);

        mageService.create(dziubich);
        mageService.create(strojek);
        mageService.create(nowak);
        mageService.create(wisnia);
        mageService.create(kulpas);
        mageService.create(wrzosek);
        mageService.create(zmudson);
        mageService.create(weektor);
        mageService.create(kobas);
        mageService.create(pawelo);

        dziubich.addApprentice(strojek);
        dziubich.addApprentice(nowak);
        dziubich.addApprentice(nowak);

        strojek.addApprentice(wrzosek);
        strojek.addApprentice(zmudson);
        strojek.addApprentice(weektor);

        nowak.addApprentice(pawelo);

        dziubich.print("\t");

    }

}
