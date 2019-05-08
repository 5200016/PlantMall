/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponClassifyUpdateComponent } from 'app/entities/sys-coupon-classify/sys-coupon-classify-update.component';
import { SysCouponClassifyService } from 'app/entities/sys-coupon-classify/sys-coupon-classify.service';
import { SysCouponClassify } from 'app/shared/model/sys-coupon-classify.model';

describe('Component Tests', () => {
    describe('SysCouponClassify Management Update Component', () => {
        let comp: SysCouponClassifyUpdateComponent;
        let fixture: ComponentFixture<SysCouponClassifyUpdateComponent>;
        let service: SysCouponClassifyService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponClassifyUpdateComponent]
            })
                .overrideTemplate(SysCouponClassifyUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysCouponClassifyUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCouponClassifyService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysCouponClassify(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysCouponClassify = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysCouponClassify();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysCouponClassify = entity;
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
