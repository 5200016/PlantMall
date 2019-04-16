/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysOrderUpdateComponent } from 'app/entities/sys-order/sys-order-update.component';
import { SysOrderService } from 'app/entities/sys-order/sys-order.service';
import { SysOrder } from 'app/shared/model/sys-order.model';

describe('Component Tests', () => {
    describe('SysOrder Management Update Component', () => {
        let comp: SysOrderUpdateComponent;
        let fixture: ComponentFixture<SysOrderUpdateComponent>;
        let service: SysOrderService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysOrderUpdateComponent]
            })
                .overrideTemplate(SysOrderUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysOrderUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysOrderService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysOrder(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysOrder = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysOrder();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysOrder = entity;
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
