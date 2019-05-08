/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponUpdateComponent } from 'app/entities/sys-coupon/sys-coupon-update.component';
import { SysCouponService } from 'app/entities/sys-coupon/sys-coupon.service';
import { SysCoupon } from 'app/shared/model/sys-coupon.model';

describe('Component Tests', () => {
    describe('SysCoupon Management Update Component', () => {
        let comp: SysCouponUpdateComponent;
        let fixture: ComponentFixture<SysCouponUpdateComponent>;
        let service: SysCouponService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponUpdateComponent]
            })
                .overrideTemplate(SysCouponUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysCouponUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCouponService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysCoupon(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysCoupon = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysCoupon();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysCoupon = entity;
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
