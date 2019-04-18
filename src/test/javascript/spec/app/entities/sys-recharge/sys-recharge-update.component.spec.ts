/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysRechargeUpdateComponent } from 'app/entities/sys-recharge/sys-recharge-update.component';
import { SysRechargeService } from 'app/entities/sys-recharge/sys-recharge.service';
import { SysRecharge } from 'app/shared/model/sys-recharge.model';

describe('Component Tests', () => {
    describe('SysRecharge Management Update Component', () => {
        let comp: SysRechargeUpdateComponent;
        let fixture: ComponentFixture<SysRechargeUpdateComponent>;
        let service: SysRechargeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysRechargeUpdateComponent]
            })
                .overrideTemplate(SysRechargeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysRechargeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysRechargeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysRecharge(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysRecharge = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysRecharge();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysRecharge = entity;
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
