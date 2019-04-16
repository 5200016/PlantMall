/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysReviewUpdateComponent } from 'app/entities/sys-review/sys-review-update.component';
import { SysReviewService } from 'app/entities/sys-review/sys-review.service';
import { SysReview } from 'app/shared/model/sys-review.model';

describe('Component Tests', () => {
    describe('SysReview Management Update Component', () => {
        let comp: SysReviewUpdateComponent;
        let fixture: ComponentFixture<SysReviewUpdateComponent>;
        let service: SysReviewService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysReviewUpdateComponent]
            })
                .overrideTemplate(SysReviewUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysReviewUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysReviewService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysReview(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysReview = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysReview();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysReview = entity;
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
