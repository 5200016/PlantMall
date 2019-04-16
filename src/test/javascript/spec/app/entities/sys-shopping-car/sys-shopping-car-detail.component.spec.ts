/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysShoppingCarDetailComponent } from 'app/entities/sys-shopping-car/sys-shopping-car-detail.component';
import { SysShoppingCar } from 'app/shared/model/sys-shopping-car.model';

describe('Component Tests', () => {
    describe('SysShoppingCar Management Detail Component', () => {
        let comp: SysShoppingCarDetailComponent;
        let fixture: ComponentFixture<SysShoppingCarDetailComponent>;
        const route = ({ data: of({ sysShoppingCar: new SysShoppingCar(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysShoppingCarDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysShoppingCarDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysShoppingCarDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysShoppingCar).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
