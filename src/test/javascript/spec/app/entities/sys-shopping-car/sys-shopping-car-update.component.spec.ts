/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysShoppingCarUpdateComponent } from 'app/entities/sys-shopping-car/sys-shopping-car-update.component';
import { SysShoppingCarService } from 'app/entities/sys-shopping-car/sys-shopping-car.service';
import { SysShoppingCar } from 'app/shared/model/sys-shopping-car.model';

describe('Component Tests', () => {
    describe('SysShoppingCar Management Update Component', () => {
        let comp: SysShoppingCarUpdateComponent;
        let fixture: ComponentFixture<SysShoppingCarUpdateComponent>;
        let service: SysShoppingCarService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysShoppingCarUpdateComponent]
            })
                .overrideTemplate(SysShoppingCarUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysShoppingCarUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysShoppingCarService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysShoppingCar(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysShoppingCar = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysShoppingCar();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysShoppingCar = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
