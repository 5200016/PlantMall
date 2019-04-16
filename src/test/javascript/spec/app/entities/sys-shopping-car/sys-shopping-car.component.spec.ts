/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysShoppingCarComponent } from 'app/entities/sys-shopping-car/sys-shopping-car.component';
import { SysShoppingCarService } from 'app/entities/sys-shopping-car/sys-shopping-car.service';
import { SysShoppingCar } from 'app/shared/model/sys-shopping-car.model';

describe('Component Tests', () => {
    describe('SysShoppingCar Management Component', () => {
        let comp: SysShoppingCarComponent;
        let fixture: ComponentFixture<SysShoppingCarComponent>;
        let service: SysShoppingCarService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysShoppingCarComponent],
                providers: []
            })
                .overrideTemplate(SysShoppingCarComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysShoppingCarComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysShoppingCarService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysShoppingCar(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysShoppingCars[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
